package us.universalpvp.iguns.explosives;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by avigh on 8/15/2016.
 */
public abstract class Explosive {

    private final String name;
    private final ExplosiveType type;
    private final ExplosionSize size;

    public Explosive(String name, ExplosiveType type, ExplosionSize size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public ExplosionSize getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public ExplosiveType getType() {
        return type;
    }

    public abstract ItemStack getBukkitItemStack();

    public abstract String getPermission();

    public abstract long getCountdownTime();

    public abstract boolean isIncendiary();

    public abstract void onDrop(PlayerDropItemEvent e);

    public abstract void onThrow(PlayerInteractEvent e);

    public abstract void onPlace(BlockPlaceEvent e);
}
