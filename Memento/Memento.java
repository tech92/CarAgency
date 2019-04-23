package Memento;

import delegatorsDefinitions.ISeaVehicle;
import delegatorsDefinitions.IVehicle;

import java.util.Vector;

class Memento {
    private Vector<IVehicle> vehicleList;
    private Vector<ISeaVehicle> seaList;
    private int totalMileage;

    Memento(Vector<IVehicle> vehicles,Vector<ISeaVehicle> sea, int mileage) {
        vehicleList = vehicles;
        seaList=sea;
        totalMileage = mileage;
    }

    Vector<IVehicle> getVehicleList() {
        return vehicleList;
    }

    int getTotalMileage() {
        return totalMileage;
    }

    Vector<ISeaVehicle> getSeaList() {
        return seaList;
    }
}
