package us.universalpvp.iguns.explosives;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by avigh on 8/15/2016.
 */
public enum ExplosionSize {

    FAKE(0F), SMALL(4F), MEDIUM(10F), LARGE(16F), MASSIVE(24F);

    private float power;

    ExplosionSize(float power) {
        this.power = power;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public static void createExplosion(ExplosionSize type, Player player, Location location, boolean isIncendiary) {
        player.getWorld().createExplosion(location, type.getPower(), isIncendiary);
    }
}
