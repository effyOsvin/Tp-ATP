class MermaidFactory extends Factory {

    public static String fraction = MermaidFactory.class.getTypeName();

    @Override
    AirForce createAirForce() {
        return new AirForce(60, 20, 0, fraction);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(70, 30, 0, fraction);
    }

    @Override
    WaterForce createWaterForce() {
        return new WaterForce(80, 40, 0, fraction);
    }
}
