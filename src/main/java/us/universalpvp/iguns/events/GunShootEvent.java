package us.universalpvp.iguns.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.iguns.manager.Gun;

/**
 * Created by avigh on 8/13/2016.
 */
public class GunShootEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player shooter;
    private final Gun gun;
    private Projectile bullet;

    public GunShootEvent(Player shooter, Gun gun, Projectile bullet) {
        this.shooter = shooter;
        this.gun = gun;
        this.bullet = bullet;
    }

    public Gun getGun() {
        return gun;
    }

    public Player getShooter() {
        return shooter;
    }

    public Projectile getBullet() {
        return bullet;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
