package us.universalpvp.iguns.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.universalpvp.iguns.events.GunEquipEvent;
import us.universalpvp.iguns.events.GunUnequipEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by avigh on 8/12/2016.
 */
public class PlayerGunRegistry {

    private final Map<Player, Set<Gun>> equippedGuns = new HashMap<>();

    public void equipGun(Player player, Gun gun) {
        if (!equippedGuns.containsKey(player)) equippedGuns.put(player, new HashSet<>());
        equippedGuns.get(player).add(gun);

        Bukkit.getPluginManager().callEvent(new GunEquipEvent(player, gun));
    }

    public void unequipGun(Player player, Gun gun) {
        if (!equippedGuns.containsKey(player)) return;
        equippedGuns.get(player).remove(gun);

        Bukkit.getPluginManager().callEvent(new GunUnequipEvent(player, gun));
    }

    public Gun getGun(Player player, GunType type) {
        if (!equippedGuns.containsKey(player))
            return null;

        for (Gun gun : equippedGuns.get(player))
            if (gun.getType() == type) return gun;

        return null;
    }

    public Set<Gun> getEquippedGuns(Player player) {
        return equippedGuns.get(player);
    }

    public boolean hasGunEquipt(Player player, GunType type) {
        return getGun(player, type) != null;
    }

}
