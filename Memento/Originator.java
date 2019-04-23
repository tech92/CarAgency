package Memento;

import delegatorsDefinitions.ISeaVehicle;
import delegatorsDefinitions.IVehicle;

import java.util.Vector;

public class Originator {
    private Vector<IVehicle> vehicleList;
    private Vector<ISeaVehicle> seaList;
    private int totalMileage;

    public void setVehicleList(Vector<IVehicle> list) {
        vehicleList = list;
    }

    public void setSeaList(Vector<ISeaVehicle> list) {
        seaList = list;
    }

    public void setTotalMileage(int mileage) {
        totalMileage = mileage;
    }

    public int getTotalMileage() {
        return totalMileage;
    }

    public Vector<IVehicle> getVehicleList() {
        return vehicleList;
    }

    public Vector<ISeaVehicle> getSeaList() {
        return seaList;
    }

    public Memento createMemento() {
        return new Memento(vehicleList,seaList, totalMileage);
    }

    public boolean setMemento(Memento memento) {
        if (memento != null) {
            vehicleList = memento.getVehicleList();
            seaList = memento.getSeaList();
            totalMileage = memento.getTotalMileage();
            return true;
        }
        return false;
    }
}
