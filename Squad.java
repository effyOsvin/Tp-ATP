import java.util.*;

public class Squad extends Unit {

    private volatile Set<Unit> allies;

    private volatile List<Squad> squads;

    Squad() {
        allies = new TreeSet<>(new UnitComparator());
        squads = new LinkedList<>();
    }

    Squad(Unit unit) {
        allies = new TreeSet<>(new UnitComparator());
        this.addUnit(unit);
        squads = new LinkedList<>();
    }
/*
    Squad(Unit... units) {
        allies = new TreeSet<>(new UnitComparator());
        allies.addAll(Arrays.asList(units));
        squads = new LinkedList<>();
    }
*/
    private void addUnit(Unit unit) {
        allies.add(unit);
    }
 
    private synchronized Unit[] getUnits() {
        LinkedList<Unit> units = new LinkedList<>();
        for (Unit unit : allies) {
            if (this.unitAlive(unit)) {
                units.addLast(unit);
            }
        }
        return (Unit[]) allies.toArray();
    }

    private void moveSquads(Squad squad) {
        if (this.getUnitRace().equals("unknown") ||
                !this.getUnitRace().equals(squad.getUnitRace())) {
            return;
        }
        for (Unit unit : squad.getUnits()) {
            if (squad.unitAlive(unit)) {
                this.addUnit(unit);
            }
        }
    }

    private boolean unitAlive(Unit unit) {
        if (!GameObserver.getGameObserver().checkUnit(unit)) {
            this.allies.remove(unit);
            return false;
        }
        return true;
    }

    @Override
    public int getHealth() {
        int total_health = 0;
        for (Unit unit : allies) {
            if (this.unitAlive(unit)) {
                total_health += unit.getHealth();
            }
        }
        for (Squad squad : squads) {
            total_health += squad.getHealth();
        }
        return total_health;
    }

    @Override
    public void move(int x, int y) {
        for (Unit unit : allies) {
            if (this.unitAlive(unit)) {
                unit.move(x, y);
            }
        }
        for (Squad squad : squads) {
            squad.move(x, y);
        }
    }

    @Override
    public String getUnitRace() {
        if (allies.size() == 0) {
            return "unknown";
        } else {
            return ((Unit) allies.iterator()).getUnitRace();
        }
    }

    @Override
    public boolean attack(Unit enemy) {
        if (!GameObserver.getGameObserver().checkUnit(enemy) || this.allies.contains(enemy)) {
            return false;
        }
        for (Squad squad : squads) {
            squad.attack(enemy);
        }
        for (Unit unit : allies) {
            if (this.unitAlive(unit)) {
                unit.attack(enemy);
            }
        }
        return true;
    }

    public void attack(Squad squad) {
        if (this.getUnitRace().equals(squad.getUnitRace()))
            return;
        for (Unit unit : squad.getUnits()) {
            this.attack(unit);
        }
    }
}
