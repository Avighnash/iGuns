package us.universalpvp.iguns.explosives;

import org.bukkit.Material;
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
    public double getCountdownTime() {
        return 100;
    }
}
