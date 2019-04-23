package carFactory;

import classes.Amphibian;
import classes.Cruise;
import classes.Frigate;
import delegatorsDefinitions.IVehicle;
import classes.HybridAirplane;
import gui.AddVehicleWindow;

import javax.swing.Icon;

public class SeaVehicleFactory implements IFactory {
    @Override
    public IVehicle makeVehicle(String type, AddVehicleWindow window) throws NumberFormatException {
        IVehicle tmpVehicle = null;
        String[] tmpData;
        boolean tmpBool = false;
        Icon tmpIcon = window.getPicture();
        switch (type) {
            case "Frigate":
                tmpData = window.getFrigateData();
                if (tmpData[3].equals("true"))
                    tmpBool = true;
                tmpVehicle = new Frigate(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), tmpBool, tmpIcon);
                break;
            case "Cruise":
                tmpData = window.getCruiseData();
                tmpVehicle = new Cruise(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), Integer.parseInt(tmpData[3]), Integer.parseInt(tmpData[4]), tmpData[5], tmpIcon);
                break;
            case "Amphibian":
                tmpData = window.getAmphibianData();
                if (tmpData[4].equals("true"))
                    tmpBool = true;
                tmpVehicle = new Amphibian(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), Integer.parseInt(tmpData[3]), tmpBool, tmpData[5], Integer.parseInt(tmpData[6]), Integer.parseInt(tmpData[7]), tmpIcon);
                break;
            case "Hybrid Airplane":
                tmpData = window.getHybridAirplaneData();
                if (tmpData[5].equals("true"))
                    tmpBool = true;
                tmpVehicle = new HybridAirplane(tmpData[0], Integer.parseInt(tmpData[1]), Integer.parseInt(tmpData[2]), Integer.parseInt(tmpData[3]), Integer.parseInt(tmpData[4]), tmpBool, tmpData[6], Integer.parseInt(tmpData[7]), tmpIcon);
                break;
        }
        return tmpVehicle;
    }
}
