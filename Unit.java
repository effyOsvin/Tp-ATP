import java.util.PriorityQueue;
import javafx.util.Pair;

public class Unit {

    private int health;
    private int damage;
    private int protection;
    private String unitFraction;

    //атакуют врагов с минимальным здоровьем
    private PriorityQueue<Unit> enemies;

    private Pair<Integer, Integer> coords;

    Unit(int health, int damage, int protection, String unitFraction) {
        this.health = health;
        this.damage = damage;
        this.protection = protection;
        this.unitFraction = unitFraction;
        enemies = new PriorityQueue<>(1, new EnemyComparator());
    }

    int getHealth() {
        return this.health;
    }

    public Pair<Integer, Integer> getCoords() {
        return coords;
    }

    public void setCoords(int x, int y) {
        this.coords = new Pair<>(x, y);
    }

    String getUnitFraction() {
        return unitFraction;
    }

    public boolean attack(Unit otherUnit) {
        return otherUnit.takeDamage(this, damage);
    }

    private boolean takeDamage(Unit enemy, int damage) {
        health = Math.max(0, health - damage + protection);
        if (health > 0) {
            enemies.add(enemy);
            return false;
        } else {
            GameWorld.getGameWorld().discardUnit(this); // юнит мертв
            return true;
        }
    }
}
