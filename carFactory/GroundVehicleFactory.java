package carFactory;

import classes.Bicycle;
import classes.ElectricBicycle;
import classes.Jeep;
import delegatorsDefinitions.IVehicle;
import gui.AddVehicleWindow;

import javax.swing.Icon;

public class GroundVehicleFactory implements IFactory {
    @Override
    public IVehicle makeVehicle(String type, AddVehicleWindow window) throws NumberFormatException {
        IVehicle tmpVehicle = null;
        String[] tmpData;
        Icon tmpIcon = window.getPicture();
        switch (type) {
            case "Jeep":
                tmpData = window.getJeepData();
                tmpVehicle = new Jeep(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), Integer.parseInt(tmpData[3]), tmpIcon);
                break;
            case "Bicycle":
                tmpData = window.getBicycleData();
                tmpVehicle = new Bicycle(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), tmpData[3], tmpIcon);
                break;
            case "Electric Bicycle":
                tmpData = window.getElectricBicycleData();
                tmpVehicle = new ElectricBicycle(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), Integer.parseInt(tmpData[3]), tmpData[4], tmpIcon);
                break;
        }
        return tmpVehicle;
    }
}
