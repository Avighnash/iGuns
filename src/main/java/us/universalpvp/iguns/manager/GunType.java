package us.universalpvp.iguns.manager;

/**
 * Created by avigh on 8/12/2016.
 */
public enum GunType {

    CARBINE(5.5, 1),
    RIFLE(8, 1),
    SHOTGUNS(8, 1),
    REVOLVER(5, 1),
    PISTOL(5, 1),
    ASUALT_RIFLE(5.3, 1),
    SNIPER(15, 1),
    ONE_SHOT_ONE_KILL(20, 1);

    private double damage;
    private float weight;

    GunType(double damage, float weight) {
        this.damage = damage;
        this.weight = weight;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
