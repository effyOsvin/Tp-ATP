public class Main {

    public static void main(String[] args) {

        GameWorld world = GameWorld.getGameWorld();

        Client brave = new Client(new MermaidFactory());
        Client cunning = new Client(new WitchFactory());
        Client prosperous = new Client(new FairyFactory());

        brave.createWaterForce();
        cunning.createAirForce();
        prosperous.createGroundForce();
    }
}
