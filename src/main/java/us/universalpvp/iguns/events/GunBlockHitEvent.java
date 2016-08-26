package us.universalpvp.iguns.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import us.universalpvp.iguns.manager.Gun;

/**
 * Created by avigh on 8/13/2016.
 */
public class GunBlockHitEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Gun gun;
    private Projectile bullet;

    public GunBlockHitEvent(Gun gun, Projectile bullet) {
        this.gun = gun;
        this.bullet = bullet;
    }

    public Gun getGun() {
        return gun;
    }

    public Projectile getBullet() {
        return bullet;
    }

    public Block getBlock() {
        Location loc = bullet.getLocation();
        Vector vec = bullet.getVelocity();
        Location loc2 = new Location(loc.getWorld(),
                loc.getX() + vec.getX(), loc.getY() + vec.getY(), loc.getZ() + vec.getZ());

        return bullet.getWorld().getBlockAt(loc2);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
