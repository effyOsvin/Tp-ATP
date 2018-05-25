import java.util.List;

//структурный паттерн - Адаптер
public class Client {

    private Factory factory;
    private GameObserver observer;
    private GameWorld world;

    private ClientInterface clientInterface = null;

    public Client(Factory factory) {
        this.factory = factory;
        observer = GameObserver.getGameObserver();
        world = GameWorld.getGameWorld();
    }

    public void setClientInterface() {
        this.clientInterface = new ClientInterface(this);
    }

    public boolean giveOrder() {
        return this.clientInterface.run();
    }

    public void receiveCommand(CreateCommand command) {
        command.execute(this);
    }

    public void createAirForce() {
        if (observer.checkAmount()) {
            Unit unit = factory.createAirForce();
            unit.setCoords(world.generateCoords());
            observer.handleUnit(unit);
        }
    }

    public void createGroundForce() {
        if (observer.checkAmount()) {
            Unit unit = factory.createGroundForce();
            unit.setCoords(world.generateCoords());
            observer.handleUnit(unit);
        }
    }

    public void createWaterForce() {
        if (observer.checkAmount()) {
            Unit unit = factory.createWaterForce();
            unit.setCoords(world.generateCoords());
            observer.handleUnit(unit);
        }
    }

    public List<Unit> getUnits(boolean proper) {
        return observer.getUnits(this.getClientFactory(), proper);
    }

    public boolean properUnit(int x, int y, boolean proper) {
        if (x < 0 || x >= GameWorldConfig.GAME_MAP_SIZE) {
            return false;
        }
        if (y < 0 || y >= GameWorldConfig.GAME_MAP_SIZE) {
            return false;
        }
        if (world.getUnit(x, y) == null) {
            return false;
        }
        return world.getUnit(x, y).getUnitRace().equals(getClientFactory()) == proper;
    }

    public boolean makeAttack(int allyX, int allyY, int enemyX, int enemyY) {
        return world.getUnit(allyX, allyY).attack(world.getUnit(enemyX, enemyY));
    }

    public boolean makeMarch(int x, int y, int deltaX, int deltaY) {
        if (x + deltaX < 0 || x + deltaX >= GameWorldConfig.GAME_MAP_SIZE) {
            return true;
        }
        if (y + deltaY < 0 || y + deltaY >= GameWorldConfig.GAME_MAP_SIZE) {
            return true;
        }
        if (world.getUnit(x + deltaX, y + deltaY) != null) {
            return true;
        }
        world.getUnit(x, y).march(deltaX, deltaY);
        world.unitMarch(x, y, x + deltaX, y + deltaY);
        return false;
    }

    public void wrapUnit(int x, int y, int wrapCode) {
        switch (wrapCode) {
            case 1:
                replaceWrappedUnit(world.getUnit(x, y),
                        new AttackImprovedForce(world.getUnit(x, y)));
                break;
            case 2:
                replaceWrappedUnit(world.getUnit(x, y),
                        new ProtectionImprovedForce(world.getUnit(x, y)));
                break;
        }
    }

    private void replaceWrappedUnit(Unit wrapped, Unit wrapper) {
        observer.discardUnit(wrapped);
        observer.handleUnit(wrapper);
    }

    private String getClientFactory() {
        return this.factory.getClass().getTypeName();
    }
}
