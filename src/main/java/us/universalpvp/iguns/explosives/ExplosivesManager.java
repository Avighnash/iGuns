package us.universalpvp.iguns.explosives;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avigh on 8/11/2016.
 */
public class ExplosivesManager {

    private final List<Explosive> registeredExplosives = new ArrayList<>();

    public List<Explosive> getRegisteredExplosives() {
        return registeredExplosives;
    }

    public void registerExplosive(Explosive explosive) {
        registeredExplosives.add(explosive);
    }

    public void unregisterExplosive(Explosive explosive) {
        registeredExplosives.remove(explosive);
    }

    public Explosive getExplosiveByName(String name) {
        for (Explosive explosive : registeredExplosives) {
            if (explosive.getName() == name) {
                return explosive;
            }
        }
        return null;
    }
}