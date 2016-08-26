package us.universalpvp.iguns;

import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.iguns.manager.GunListeners;

/**
 * Created by avigh on 8/11/2016.
 */
public class IGunsMain extends JavaPlugin {


    @Override
    public void onEnable() {
        new GunListeners(this);
    }


}
