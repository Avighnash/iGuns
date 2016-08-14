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
 
 /**
  * Credit to @2008Choco too for this idea with most of the code.
  */
public class PlayerGunRegistry {

    private Map<Player, Set<Gun>> equipedGuns = new HashMap<>();

    public void equipGun(Player player, Gun gun) {
        if (!equipedGuns.containsKey(player)) equipedGuns.put(player, new HashSet<>());
        equipedGuns.get(player).add(gun);

        Bukkit.getPluginManager().callEvent(new GunEquipEvent(player, gun));
    }

    public void unequipGun(Player player, Gun gun) {
        if (!equipedGuns.containsKey(player)) return;
        equipedGuns.get(player).remove(gun);

        Bukkit.getPluginManager().callEvent(new GunUnequipEvent(player, gun));
    }

    public Gun getGun(Player player, GunType type) {
        if (!equipedGuns.containsKey(player))
            return null;

        for (Gun gun : equipedGuns.get(player))
            if (gun.getType().equals(type)) return gun;

        return null;
    }

    public Set<Gun> getEquippedGuns(Player player) {
        return equipedGuns.get(player);
    }

    public boolean hasGunEquipt(Player player, GunType type) {
        return getGun(player, type) != null;
    }

}
