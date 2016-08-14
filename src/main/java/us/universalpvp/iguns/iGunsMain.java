package us.universalpvp.iguns;

import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.iguns.manager.GunManager;
import us.universalpvp.iguns.manager.PlayerGunRegistry;

/**
 * Created by avigh on 8/11/2016.
 */
public class iGunsMain extends JavaPlugin {

    private PlayerGunRegistry registry;
    private GunManager manager;

    @Override
    public void onEnable() {
        registry = new PlayerGunRegistry();
        manager = new GunManager();
    }

    public GunManager getManager() {
        return manager;
    }

    public PlayerGunRegistry getRegistry() {
        return registry;
    }
}
