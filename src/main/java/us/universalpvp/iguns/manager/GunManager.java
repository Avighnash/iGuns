package us.universalpvp.iguns.manager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by avigh on 8/11/2016.
 */
public class GunManager {

    private final Set<Gun> registeredGuns = new HashSet<>();

    public Set<Gun> getRegisteredGuns() {
        return registeredGuns;
    }

    public void registerGun(Gun gun) {
        registeredGuns.add(gun);
    }

    public void unregisterGun(Gun gun) {
        registeredGuns.remove(gun);
    }

    public Gun getGunByName(String name) {
        for (Gun gun : registeredGuns) {
            if (gun.getName().equals(name)) {
                return gun;
            }
        }
        return null;
    }
}