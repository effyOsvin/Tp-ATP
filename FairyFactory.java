class FairyFactory extends Factory {

    private static final String race = FairyFactory.class.getTypeName();

    @Override
    AirForce createAirForce() {
        return new AirForce(UnitCharacteristics.Fairy_Air_Health,
                UnitCharacteristics.Fairy_Air_Damage,
                UnitCharacteristics.Fairy_Air_Protect,
                UnitCharacteristics.Fairy_Air_Range,
                race);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(UnitCharacteristics.Fairy_Ground_Health,
                UnitCharacteristics.Fairy_Ground_Damage,
                UnitCharacteristics.Fairy_Ground_Protect,
                UnitCharacteristics.Fairy_Air_Range,
                race);
    }

    @Override
    WaterForce createWaterForce() {
        return new WaterForce(UnitCharacteristics.Fairy_Water_Health,
                UnitCharacteristics.Fairy_Water_Damage,
                UnitCharacteristics.Fairy_Water_Protect,
                UnitCharacteristics.Fairy_Air_Range,
                race);
    }
}
