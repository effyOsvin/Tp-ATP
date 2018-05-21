import javafx.util.Pair;
import java.util.Random;
// Порождающий паттерн - Синглтон
// Поведенчиский паттерн - Наблюдатель
public class GameWorld {

    private static GameWorld gameWorld = null;
    private static Unit[][] map;

    private GameWorld() {
        map = new Unit[GameWorldConfig.GAME_MAP_SIZE][GameWorldConfig.GAME_MAP_SIZE];
    }

    public static GameWorld getGameWorld() {
        if (gameWorld == null) {
            gameWorld = new GameWorld();
        }
        return gameWorld;
    }

    public Pair<Integer, Integer> generateCoords() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(GameWorldConfig.GAME_MAP_SIZE);
            y = random.nextInt(GameWorldConfig.GAME_MAP_SIZE);
        } while (map[x][y] != null);
        return new Pair<>(x, y);
    }

    public Unit getUnit(int x, int y) {
        return map[x][y];
    }

    public void setUnit(Unit unit) {
        map[unit.getCoords().getKey()][unit.getCoords().getValue()] = unit;
    }

    public void unitMove(int prevX, int prevY, int nextX, int nextY) {
        map[nextX][nextY] = map[prevX][prevY];
        map[prevX][prevY] = null;
    }

    void discardUnit(Unit unit) {
        int x = unit.getCoords().getKey(),
                y = unit.getCoords().getValue();
        map[x][y] = null;
    }
}
