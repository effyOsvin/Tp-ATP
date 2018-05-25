import java.util.Random;

public class ProtectionImprovedForce extends ImprovedForce {

    private Unit originalForce;
    private float misAttackValue;

    public ProtectionImprovedForce(Unit originalForce) {
        super(originalForce);
        this.originalForce = originalForce;
        this.misAttackValue = Math.max(0.0f, Math.min(1.0f, ((new Random()).nextInt(11)) / 10.0f));
    }

    @Override
    public synchronized void takeDamage(Unit enemy, int damage) {
        originalForce.takeDamage(enemy, (int) ((1 - this.misAttackValue) * damage));
    }
}
