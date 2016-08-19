package us.universalpvp.iguns.explosives;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by avigh on 8/15/2016.
 */
public class C4 extends Explosive {

    public C4() {
        super("C4", ExplosiveType.PLACABLE, ExplosionSize.SMALL);
    }

    @Override
    public ItemStack getBukkitItemStack() {
        return new ItemStack(Material.POTION);
    }

    @Override
    public String getPermission() {
        return "Lol";
    }

    @Override
    public long getCountdownTime() {
        return 100;
    }

    @Override
    public boolean isIncendiary() {
        return false;
    }

    @Override
    public void onDrop(PlayerDropItemEvent e) {

    }

    @Override
    public void onThrow(PlayerInteractEvent e) {

    }

    @Override
    public void onPlace(BlockPlaceEvent e) {

    }
}
