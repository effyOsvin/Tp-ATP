import java.util.HashSet;

public class GameWorld {

    private static GameWorld gameWorld = null;

    private HashSet<Unit> units;

    private GameWorld() {
        units = new HashSet<>();
    }

    public static GameWorld getGameWorld() {
        if (gameWorld == null) {
            gameWorld = new GameWorld();
        }
        return gameWorld;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void discardUnit(Unit unit) {
        units.remove(unit);
    }
}
