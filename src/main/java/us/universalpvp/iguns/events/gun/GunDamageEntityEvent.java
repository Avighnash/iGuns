package us.universalpvp.iguns.events.gun;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import us.universalpvp.iguns.manager.Gun;

/**
 * Created by avigh on 8/13/2016.
 */
public class GunDamageEntityEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Projectile projectile;
    private final Entity entity;
    private final Gun gun;
    private double damage;
    private final Player player;

    public GunDamageEntityEvent(Projectile projectile, Entity entity, Gun gun, double damage, Player player) {
        this.entity = entity;
        this.projectile = projectile;
        this.gun = gun;
        this.damage = damage;
        this.player = player;
    }

    public Gun getGun() {
        return gun;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public Entity getEntity() {
        return entity;
    }

    public double getDamage() {
        return damage;
    }

    public Player getPlayer() {
        return player;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public boolean isHeadshot() {
        double y = projectile.getLocation().getY(),
                shotY = entity.getLocation().getY();
        boolean headshot = y - shotY > 1.30d;

        return headshot;
    }

    public boolean isBackstab() {
        Vector attackerDirection = player.getLocation().getDirection();
        Vector victimDirection = entity.getLocation().getDirection();

        return attackerDirection.dot(victimDirection) > 0;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
