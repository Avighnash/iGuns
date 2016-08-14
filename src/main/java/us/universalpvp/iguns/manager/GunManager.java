package us.universalpvp.iguns.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avigh on 8/11/2016.
 */
public class GunManager {

    private final List<Gun> registeredGuns = new ArrayList<>();

    public List<Gun> getRegisteredGuns() {
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
            if (gun.getName() == name) {
                return gun;
            }
        }
        return null;
    }
}