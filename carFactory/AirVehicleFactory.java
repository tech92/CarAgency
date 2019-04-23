package carFactory;

import classes.SpyGlider;
import classes.ToyGlider;
import delegatorsDefinitions.IVehicle;
import gui.AddVehicleWindow;

import javax.swing.Icon;

public class AirVehicleFactory implements IFactory {
    @Override
    public IVehicle makeVehicle(String type, AddVehicleWindow window) {
        IVehicle tmpVehicle = null;
        Icon tmpIcon = window.getPicture();
        switch (type) {
            case "Toy Glider":
                tmpVehicle = new ToyGlider(tmpIcon);
                break;
            case "Spy Glider":
                tmpVehicle = new SpyGlider(window.getSpyGliderData(), tmpIcon);
                break;
        }
        return tmpVehicle;
    }
}
