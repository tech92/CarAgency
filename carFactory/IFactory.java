package carFactory;

import delegatorsDefinitions.IVehicle;
import gui.AddVehicleWindow;

public interface IFactory {
    IVehicle makeVehicle(String type, AddVehicleWindow window);
}
