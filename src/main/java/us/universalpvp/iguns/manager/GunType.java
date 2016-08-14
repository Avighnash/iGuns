package us.universalpvp.iguns.manager;

/**
 * Created by avigh on 8/12/2016.
 */
public enum GunType {

    CARBINE(1), RIFLE(1), SHOTGUNS(1), REVOLVER(1), PISTOL(1), ONE_SHOT_ONE_KILL(20);

    private double damage;

    GunType(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
