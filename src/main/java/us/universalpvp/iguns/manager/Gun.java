package us.universalpvp.iguns.manager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.universalpvp.iguns.clip.Clip;
import us.universalpvp.iguns.IGunsMain;

/**
 * Created by avigh on 8/11/2016.
 */
public abstract class Gun {

    private final String name;
    private final GunType type;
    private final Clip clip;

    private final GunListeners factory = new GunListeners(new IGunsMain());
    private int ammo = this.getClip().getSize();

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

    public Clip getClip() {
        return clip;
    }

    public abstract ItemStack getBukkitItemStack();

    public abstract double getReloadTime();

    public abstract float getRecoil();

    public abstract String getPermission();

    public abstract String noPermission();

    public abstract double getMagnitude();

    public abstract long getRateOfFire();

    public abstract double getVelocityMultiplier();

    public abstract Projectile onInteract(PlayerInteractEvent e);

    public abstract void onHit(EntityDamageByEntityEvent e);

    public abstract void onBlockHit(ProjectileHitEvent e);

    public void shoot(Player player, Projectile projectile) {
        if (player.getInventory().getItemInMainHand().isSimilar(this.getBukkitItemStack())) {

            if (ammo > 0) {
                projectile.setVelocity(projectile.getVelocity().multiply(this.getVelocityMultiplier()));
                projectile.setShooter(player);

                ammo--;
            } else {
                reload(player);
            }
        }
    }

    public void reload(Player player) {
        Clip clip = this.clip;

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack.isSimilar(clip.getBukkitItemStack())) {
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }

    }
}

