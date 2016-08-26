package us.universalpvp.iguns.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import us.universalpvp.iguns.IGunsMain;
import us.universalpvp.iguns.api.IGunsAPI;
import us.universalpvp.iguns.events.*;
import us.universalpvp.iguns.manager.interfaces.Scopable;
import us.universalpvp.iguns.manager.interfaces.Trailable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by avigh on 8/11/2016.
 */
public class GunListeners implements Listener {

    private final PlayerGunRegistry registry;
    private final GunManager manager;
    private final IGunsMain main;

    private final Map<ItemStack, Long> cooldown = new HashMap<>();

    public GunListeners(IGunsMain main) {
        main.getServer().getPluginManager().registerEvents(this, main);

        this.main = main;
        this.registry = IGunsAPI.getAPI().getRegistry();
        this.manager = IGunsAPI.getAPI().getGunManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_BLOCK && p.getInventory().getItemInMainHand() != null) {
            Gun toShoot = null;
            for (Gun gun : registry.getEquippedGuns(p)) {
                if (p.getInventory().getItemInMainHand().isSimilar(gun.getBukkitItemStack())) {
                    toShoot = gun;
                    break;
                }
            }

            if (toShoot == null) {
                for (Gun g : manager.getRegisteredGuns()) {
                    if (p.getInventory().getItemInMainHand().isSimilar(g.getBukkitItemStack())) {
                        toShoot = g;
                        registry.equipGun(p, g);
                        break;
                    }
                }
            }

            Projectile bullet = toShoot.onInteract(e);
            bullet.setMetadata(toShoot.getName(), new FixedMetadataValue(main, toShoot.getName()));
            bullet.getLocation().setPitch(bullet.getLocation().getPitch() + toShoot.getRecoil());

            long rof = toShoot.getRateOfFire();

            if (System.currentTimeMillis() - cooldown.getOrDefault(toShoot.getBukkitItemStack(), rof) < rof)
                return;
            cooldown.put(toShoot.getBukkitItemStack(), System.currentTimeMillis());

            Bukkit.getPluginManager().callEvent(new GunShootEvent(p, toShoot, bullet));
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        final Entity entity = e.getEntity();

        if (e.getDamager() instanceof Player) {
            if (entity instanceof Projectile) {
                Projectile bullet = (Projectile) e.getEntity();

                manager.getRegisteredGuns().stream().filter(gun -> bullet.hasMetadata(gun.getName()))
                        .filter(gun -> gun.getName().equals(bullet.getMetadata(gun.getName()).toString())).forEach(gun -> {
                    gun.onHit(e);

                    Bukkit.getPluginManager().callEvent(new GunDamageEntityEvent(bullet, entity, gun,
                            e.getDamage(), (Player) e.getDamager()));
                });
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();

        manager.getRegisteredGuns().stream().filter(gun -> p.getItemInHand().isSimilar(gun.getBukkitItemStack()))
                .filter(gun -> gun instanceof Scopable).forEach(gun -> {
            if (e.isSneaking()) {
                ((Scopable) gun).scope(p);
                Bukkit.getPluginManager().callEvent(new PlayerScopeEvent(p, gun));
            } else {
                ((Scopable) gun).unscope(p);
                Bukkit.getPluginManager().callEvent(new PlayerUnscopeEvent(p, gun));
            }
        });
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        Projectile bullet = e.getEntity();

        manager.getRegisteredGuns().stream().filter(gun -> bullet.hasMetadata(gun.getName()))
                .filter(gun -> gun.getName().equals(bullet.getMetadata(gun.getName()).toString()))
                .filter(gun -> gun instanceof Trailable).forEach(gun -> {
            Bukkit.getScheduler().runTaskTimer(main, () -> {
                ((Trailable) gun).trail(bullet);
            }, 0, 5);
        });
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile bullet = e.getEntity();

        manager.getRegisteredGuns().stream().filter(gun -> bullet.hasMetadata(gun.getName()))
                .filter(gun -> gun.getName().equals(bullet.getMetadata(gun.getName()).toString()))
                .forEach(gun -> {
                    gun.onBlockHit(e);

                    Bukkit.getPluginManager().callEvent(new GunBlockHitEvent(gun, bullet));
                });
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();

        manager.getRegisteredGuns().stream().filter(gun -> p.getInventory().getItemInMainHand()
                .isSimilar(gun.getBukkitItemStack())).forEach(gun ->
                p.setWalkSpeed(gun.getType().getWeight()));
    }
}
