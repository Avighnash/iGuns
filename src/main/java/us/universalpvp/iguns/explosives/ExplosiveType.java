package us.universalpvp.iguns.explosives;

/**
 * Created by avigh on 8/15/2016.
 */
public enum ExplosiveType {

    DROPABLE, PLACABLE, THROWABLE(2);

    ExplosiveType() {
        //do nothing
    }

    private double velocity;

    ExplosiveType(double velocity) {
        this.velocity = velocity;
    }


    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
