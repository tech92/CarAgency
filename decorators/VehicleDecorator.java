package decorators;

import delegatorsDefinitions.IVehicle;

import javax.swing.Icon;


public abstract class VehicleDecorator implements IVehicle {
    private IVehicle vehicle;

    VehicleDecorator(IVehicle v) {
        vehicle = v;
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof IVehicle)
            return vehicle.equals(other);
        return false;
    }

    @Override
    public String toString() {
        return vehicle.toString();
    }

    @Override
    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
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
    public void setMileage(int km) {vehicle.setMileage(km);}
}
