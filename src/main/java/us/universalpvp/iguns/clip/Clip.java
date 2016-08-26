package us.universalpvp.iguns.clip;

import org.bukkit.inventory.ItemStack;

/**
 * Created by avigh on 8/18/2016.
 */
public class Clip {

    private int maxStackSize;
    private int size;
    private ItemStack stack;

    public Clip(ItemStack stack, int size, int maxStackSize) {
        this.maxStackSize = maxStackSize;
        this.size = size;
        this.stack = stack;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getSize() {
        return size;
    }

    public ItemStack getBukkitItemStack() {
        return stack;
    }
}
