package us.universalpvp.iguns.manager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.universalpvp.iguns.clip.Clip;

/**
 * Created by avigh on 8/11/2016.
 */
public abstract class Gun {

    private final String name;
    private final GunType type;
    private final Clip clip;

    public Gun(String name, GunType type, Clip clip) {
        this.name = name;
        this.type = type;
        this.clip = clip;
    }

    public String getName() {
        return name;
    }

    public GunType getType() {
        return type;
    }

    public abstract ItemStack getBukkitItemStack();

    public abstract double getReloadTime();

    public abstract float getRecoil();

    public abstract String getPermission();

    public abstract double getMagnitude();

    public abstract long getRateOfFire();

    public abstract Projectile onInteract(PlayerInteractEvent e);

    public abstract void onHit(EntityDamageByEntityEvent e);

    public abstract void onBlockHit(ProjectileHitEvent e);

    public void shoot(Player player, Projectile projectile) {
        if (player.getInventory().getItemInMainHand().isSimilar(this.getBukkitItemStack())) {

        }
    }

    public void reload(Player player) {
        Clip clip = this.clip;


    }
}
