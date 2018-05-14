// порождающий паттерн - Абстрактная фабрика
abstract class Factory {

    abstract AirForce createAirForce();

    abstract GroundForce createGroundForce();

    abstract WaterForce createWaterForce();
}
