import javafx.util.Pair;

public class ImprovedForce extends Unit {

    private Unit originalForce;

    ImprovedForce(Unit unit) {
        originalForce = unit;
    }

    @Override
    public void march(int x, int y) {
        originalForce.march(x, y);
    }

    @Override
    public void setCoords(int x, int y) {
        originalForce.setCoords(x, y);
    }

    @Override
    public int getHealth() {
        return originalForce.getHealth();
    }

    @Override
    public Pair<Integer, Integer> getCoords() {
        return originalForce.getCoords();
    }

    @Override
    public String getUnitRace() {
        return originalForce.getUnitRace();
    }

    @Override
    public boolean attack(Unit enemy) {
        return originalForce.attack(enemy);
    }

    @Override
    public void takeDamage(Unit enemy, int damage) {
        originalForce.takeDamage(enemy, damage);
    }
}
