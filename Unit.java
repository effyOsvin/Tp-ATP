import javafx.util.Pair;
import java.util.TreeSet;
// паттерн Компоновщик
public class Unit {

    private int health;
    private int damage;
    private int protection;
    private int attackRange;
    private int x, y; // координаты
    private String unitRace;

    //атакуют врагов с минимальным здоровьем
    private TreeSet<Unit> enemies;

    Unit(int health, int damage, int protection, int attackRange, String unitRace) {
        this.health = health;
        this.damage = damage;
        this.protection = protection;
        this.attackRange = attackRange;
        this.unitRace = unitRace;
        enemies = new TreeSet<>(new EnemyComparator());
    }

    synchronized int getHealth() {
        return this.health;
    }

    public int getAttackRange() {
        return this.attackRange;
    }

    public synchronized Pair<Integer, Integer> getCoords() {
        return new Pair<>(x, y);
    }

    private synchronized void setCoords(int x, int y) {
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
        if (GameWorld.getGameWorld().findUnit(enemy)) {
            enemies.add(enemy);
            int distance = Math.abs(x - enemy.getCoords().getKey())
                    + Math.abs(y - enemy.getCoords().getValue());
            if (distance > attackRange) {
                return false;
            }
            enemy.takeDamage(this, damage);
            return enemy.takeDamage(this, damage);
        } else {
            enemies.remove(enemy);
            return false;
        }
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

    public void step(int deltaX, int deltaY) {
        this.setCoords(this.x + deltaX, this.y + deltaY);
    }
}
