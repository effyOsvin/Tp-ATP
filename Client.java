import javafx.util.Pair;
import java.util.HashSet;

// структурный патерн - Адаптерн
public class Client implements Runnable {

    private HashSet<Unit> forces;

    private Factory race;

    Client(Factory race) {
        this.race = race;
        this.forces = new HashSet<>();
    }

    public void createAirForce() {
        Unit unit = race.createAirForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void createGroundForce() {
        Unit unit = race.createGroundForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void createWaterForce() {
        Unit unit = race.createWaterForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void attack(Unit myUnit, Unit enemyUnit) {
        if (!forces.contains(myUnit) || forces.contains(enemyUnit))
            return;
        myUnit.attack(enemyUnit);
    }

    public String getClientRace() {
        return race.getClass().getTypeName();
    }

    @Override
    public void run() {
        ClientInterface clientInterface = new ClientInterface(this);
        clientInterface.show();
    }

    public void move(Unit myUnit, Pair<Integer, Integer> coords) {
        if (!GameWorld.getGameWorld().findUnit(myUnit))
            return;
        myUnit.move(coords);
    }
}
