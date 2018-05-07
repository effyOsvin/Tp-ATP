import java.util.ArrayList;
import java.util.List;

public class Client {

    private List<Unit> forces;

    private Factory fraction;

    Client(Factory fraction) {
        this.fraction = fraction;
        this.forces = new ArrayList<>();
    }

    public void createAirForce() {
        Unit unit = fraction.createAirForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void createGroundForce() {
        Unit unit = fraction.createGroundForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void createWaterForce() {
        Unit unit = fraction.createWaterForce();
        forces.add(unit);
        GameWorld.getGameWorld().addUnit(unit);
    }

    public void attack(Unit myUnit, Unit enemyUnit) {
        if (!forces.contains(myUnit) || forces.contains(enemyUnit))
            return;
        myUnit.attack(enemyUnit);
    }

    public void explore() {
    }

    public String getClientFraction() {
        return fraction.getClass().getTypeName();
    }
}
