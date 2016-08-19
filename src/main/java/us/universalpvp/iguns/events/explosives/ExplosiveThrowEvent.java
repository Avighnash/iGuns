package us.universalpvp.iguns.events.explosives;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.iguns.explosives.Explosive;

/**
 * Created by avigh on 8/16/2016.
 */
public class ExplosiveThrowEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Explosive explosive;

    public ExplosiveThrowEvent(Player player, Explosive explosive) {
        this.player = player;
        this.explosive = explosive;
    }

    public Explosive getExplosive() {
        return explosive;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
