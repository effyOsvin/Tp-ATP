class WitchFactory extends Factory {

    public static String fraction = WitchFactory.class.getTypeName();

    @Override
    AirForce createAirForce() {
        return new AirForce(80, 20, 0, fraction);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(90, 30, 0, fraction);
    }

    @Override
    WaterForce createInfantry() {
        return new WaterForce(70, 10, 0, fraction);
    }
}
