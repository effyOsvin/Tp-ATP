import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GameObserver {

    private static GameObserver gameObserver = null;

    private Set<Unit> units;

    private GameObserver() {
        units = new HashSet<>();
    }

    public static GameObserver getGameObserver() {
        if (gameObserver == null) {
            gameObserver = new GameObserver();
        }
        return gameObserver;
    }

    public void handleUnit(Unit unit) {
        GameWorld.getGameWorld().setUnit(unit);
        units.add(unit);
    }

    public boolean checkUnit(Unit unit) {
        return units.contains(unit);
    }

    public void discardUnit(Unit unit) {
        GameWorld.getGameWorld().discardUnit(unit);
        units.remove(unit);
    }

    public boolean checkAmount() {
        return units.size() + 2 < GameWorldConfig.GAME_MAP_SIZE * GameWorldConfig.GAME_MAP_SIZE;
    }

    public List<Unit> getUnits(String fraction, boolean proper) {
        LinkedList<Unit> allies = new LinkedList<>();
        for (Unit unit : units) {
            if (fraction.equals(unit.getUnitRace()) == proper) {
                allies.addLast(unit);
            }
        }
        return allies;
    }
}