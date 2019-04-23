package classes;

import javax.swing.Icon;
import javax.swing.border.Border;

import delegatorsDefinitions.*;

public class Cruise implements IVehicle, IMotorized, ICommercial, ISeaVehicle {
    private Vehicle vehicle;
    private Motorized motor;
    private Commercial comm;
    private SeaVehicle sea;

    public Cruise(String mod, int cap, int max, int fuelConsumption, int lifetime, String flag, Icon pic) {
        vehicle = new Vehicle(mod, cap, max, pic);
        motor = new Motorized(fuelConsumption, lifetime);
        comm = new Commercial("UNLIMITED");
        sea = new SeaVehicle(true, flag);
    }

    public String toString() {
        return vehicle.toString() + sea.toString() + "\n" + comm.toString() + motor.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof Cruise)
            return (vehicle.equals(other) && sea.equals(other) && comm.equals(other) && motor.equals(other));
        return false;
    }

    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
    }

    public boolean getWind() {
        return sea.getWind();
    }

    public String getFlag() {
        return sea.getFlag();
    }

    public void setWind(boolean wind) {
        sea.setWind(wind);
    }

    public void setFlag(String flag) {
        sea.setFlag(flag);
    }

    public String getLicenseType() {
        return comm.getLicenseType();
    }

    public int getAverageFuelConsumption() {
        return motor.getAverageFuelConsumption();
    }

    public void setAverageFuelConsumption(int fuelConsumption) {
        motor.setAverageFuelConsumption(fuelConsumption);
    }

    public int getAverageMotorLifetime() {
        return motor.getAverageMotorLifetime();
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

    @Override
    public Object cloneVehicle() {
        Cruise tmp = new Cruise(vehicle.getModel(), vehicle.getCapacity(), vehicle.getMaxSpeed(), motor.getAverageFuelConsumption(), motor.getAverageMotorLifetime(), sea.getFlag(), vehicle.getIcon());
        tmp.setMileage(vehicle.getMileage());
        return tmp;
    }

    public void setMileage(int km) {
        vehicle.setMileage(km);
    }
}
