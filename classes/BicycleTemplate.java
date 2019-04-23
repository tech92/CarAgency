package classes;

import delegatorsDefinitions.GroundVehicle;
import delegatorsDefinitions.IGroundVehicle;
import delegatorsDefinitions.Vehicle;
import delegatorsDefinitions.IVehicle;

import javax.swing.Icon;
import javax.swing.border.Border;


public abstract class BicycleTemplate implements IVehicle, IGroundVehicle {
    private Vehicle vehicle;
    private GroundVehicle ground;

    BicycleTemplate(String mod, int cap, int max, String type, Icon pic, int wheels) {
        vehicle = new Vehicle(mod, cap, max, pic);
        ground = new GroundVehicle(wheels, type);
    }

    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
    }

    public String toString() {
        return vehicle.toString() + ground.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof BicycleTemplate)
            return vehicle.equals(other) && ground.equals(other);
        return false;
    }


    public int getNumberOfWheels() {
        return ground.getNumberOfWheels();
    }


    public String getTerrainType() {
        return ground.getTerrainType();
    }

    @Override
    public String getColor() {
        return vehicle.getColor();
    }

    @Override
    public String getStatus() {
        return vehicle.getStatus();
    }

    @Override
    public Icon getIcon() {
        return vehicle.getIcon();
    }

    @Override
    public int getMileage() {
        return vehicle.getMileage();
    }

    @Override
    public String getModel() {
        return vehicle.getModel();
    }

    @Override
    public int getCapacity() {
        return vehicle.getCapacity();
    }

    @Override
    public int getMaxSpeed() {
        return vehicle.getMaxSpeed();
    }

    @Override
    public void resetMileage() {
        vehicle.resetMileage();
    }

    @Override
    public Border getBorder() {
        return vehicle.getBorder();
    }

    public void setMileage(int km) {vehicle.setMileage(km);}
}
