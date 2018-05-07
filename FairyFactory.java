class FairyFactory extends Factory {

    public static String fraction = FairyFactory.class.getTypeName();

    @Override
    AirForce createAirForce() {
        return new AirForce(85, 35, 0, fraction);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(65, 15, 0, fraction);
    }

    @Override
    WaterForce createInfantry() {
        return new WaterForce(75, 25, 0, fraction);
    }
}
