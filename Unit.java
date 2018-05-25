import javafx.util.Pair;
import java.util.TreeSet;
// паттерн Компоновщик
public abstract class Unit {

    private int health;
    private int damage;
    private int protection;
    private int attackRange;
    private int x, y; // координаты
    private String unitRace;

    //атакуют врагов с минимальным здоровьем
    private TreeSet<Unit> enemies;

    Unit( ){ }

    Unit(int health, int damage, int protection, int attackRange, String unitRace) {
        this.health = health;
        this.damage = damage;
        this.protection = protection;
        this.attackRange = attackRange;
        this.unitRace = unitRace;
        enemies = new TreeSet<>(new UnitComporator());
    }

    int getHealth() {
        return this.health;
    }

    public int getAttackRange() {
        return this.attackRange;
    }

    public Pair<Integer, Integer> getCoords() {
        return new Pair<>(x, y);
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoords(Pair<Integer, Integer> coords) {
        setCoords(coords.getKey(), coords.getValue());
    }

    String getUnitRace() {
        return unitRace;
    }

    public boolean attack(Unit enemy) {
        if (GameObserver.getGameObserver().checkUnit(enemy)) {
            enemies.add(enemy);
            int distance = Math.abs(x - enemy.getCoords().getKey())
                    + Math.abs(y - enemy.getCoords().getValue());
            if (distance > attackRange) {
                return false;
            }
            enemy.takeDamage(this, damage);
            return true;
        } else {
            enemies.remove(enemy);
            return false;
        }
    }

    public void takeDamage(Unit enemy, int damage) {
        health = Math.max(0, health - damage + protection);
        if (health > 0) {
            enemies.add(enemy);
        } else {
            GameObserver.getGameObserver().discardUnit(this); // Unit is dead
        }
    }

    public void march(int deltaX, int deltaY) {
        this.setCoords(this.x + deltaX, this.y + deltaY);
    }
}
