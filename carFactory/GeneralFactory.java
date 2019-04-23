package carFactory;

public abstract class GeneralFactory {
    public static IFactory getFactory(String type) {
        switch (type) {
            case "Sea":
                return new SeaVehicleFactory();
            case "Air":
                return new AirVehicleFactory();
            default:
                return new GroundVehicleFactory();
        }
    }
}
