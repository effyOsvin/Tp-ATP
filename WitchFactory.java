class WitchFactory extends Factory {

    public static String race = "Witch";

    @Override
    AirForce createAirForce() {
        return new AirForce(UnitCharacteristics.Witch_Air_Health,
                UnitCharacteristics.Witch_Air_Damage,
                UnitCharacteristics.Witch_Air_Protect,
                UnitCharacteristics.Witch_Air_Range,
                race);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(UnitCharacteristics.Witch_Ground_Health,
                UnitCharacteristics.Witch_Ground_Damage,
                UnitCharacteristics.Witch_Ground_Protect,
                UnitCharacteristics.Witch_Ground_Range,
                race);
    }

    @Override
    WaterForce createWaterForce() {
        return new WaterForce(UnitCharacteristics.Witch_Water_Health,
                UnitCharacteristics.Witch_Water_Damage,
                UnitCharacteristics.Witch_Water_Protect,
                UnitCharacteristics.Witch_Water_Range,
                race);
    }

    @Override
    String getFactory(){
        return race;
    }
}