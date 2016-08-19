package us.universalpvp.iguns.events.explosives;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.iguns.explosives.Explosive;

import java.util.List;

/**
 * Created by avigh on 8/16/2016.
 */
public class ExplosiveDestroyBlockEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Explosive explosive;
    private final List<Block> blockList;
    private boolean cancel;

    public ExplosiveDestroyBlockEvent(Player player, Explosive explosive, List<Block> blockList) {
        this.player = player;
        this.explosive = explosive;
        this.blockList = blockList;
        this.cancel = false;
    }

    public Explosive getExplosive() {
        return explosive;
    }

    public Player getPlayer() {
        return player;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancel = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
