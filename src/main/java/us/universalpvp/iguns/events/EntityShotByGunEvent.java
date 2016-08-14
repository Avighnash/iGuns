package us.universalpvp.iguns.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.iguns.manager.Gun;

/**
 * Created by avigh on 8/13/2016.
 */
public class EntityShotByGunEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Projectile bullet;
    private final Entity entity;
    private final Gun gun;

    public EntityShotByGunEvent(Projectile bullet, Entity entity, Gun gun) {
        this.entity = entity;
        this.bullet = bullet;
        this.gun = gun;
    }

    public Gun getGun() {
        return gun;
    }

    public Projectile getBullet() {
        return bullet;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isHeadshot() {
        double y = bullet.getLocation().getY(),
                shotY = entity.getLocation().getY();
        boolean headshot = y - shotY > 1.30d;

        return headshot;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
