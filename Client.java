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

    public void start(int count){
        createAirForce(count);
        createWaterForce(count);
        createGroundForce(count);
    }

    public boolean giveOrder() {

        return this.clientInterface.run();
    }

    public boolean giveOrderBot() {
        if(getUnits(true).size() == 0){
            return true;
        }
        return false;
    }

    public Integer countUnits(){
        System.out.println("Choose size of your army");
        return ConsoleInterface.getInput();
    }

    public void createAirForce(int count) {
        if (observer.checkAmount()) {
            for(Integer i = 0; i < count; i++) {
                Unit unit = factory.createAirForce();
                unit.setCoords(world.generateCoords());
                observer.handleUnit(unit);
            }
        }
    }

    public void createGroundForce(int count) {
        if (observer.checkAmount()) {
            for(Integer i = 0; i < count; i++) {
                Unit unit = factory.createGroundForce();
                unit.setCoords(world.generateCoords());
                observer.handleUnit(unit);
            }
        }
    }

    public void createWaterForce(int count) {
        if (observer.checkAmount()) {
            for(Integer i = 0; i < count; i++) {
                Unit unit = factory.createWaterForce();
                unit.setCoords(world.generateCoords());
                observer.handleUnit(unit);
            }
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

    private String getClientFactory() {
        return this.factory.getFactory();
    }
}