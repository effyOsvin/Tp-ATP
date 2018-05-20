class MermaidFactory extends Factory {

    public static String race = MermaidFactory.class.getTypeName();

    @Override
    AirForce createAirForce() {
        return new AirForce(UnitCharacteristics.Mermaid_Air_Health,
                UnitCharacteristics.Mermaid_Air_Damage,
                UnitCharacteristics.Mermaid_Air_Protect,
                UnitCharacteristics.Mermaid_Air_Range,
                race);
    }

    @Override
    GroundForce createGroundForce() {
        return new GroundForce(UnitCharacteristics.Mermaid_Ground_Health,
                UnitCharacteristics.Mermaid_Ground_Damage,
                UnitCharacteristics.Mermaid_Ground_Protect,
                UnitCharacteristics.Mermaid_Ground_Range,
                race);
    }

    @Override
    WaterForce createWaterForce() {
        return new WaterForce(UnitCharacteristics.Mermaid_Water_Health,
                UnitCharacteristics.Mermaid_Water_Damage,
                UnitCharacteristics.Mermaid_Water_Protect,
                UnitCharacteristics.Mermaid_Water_Range,
                race);
    }
}
