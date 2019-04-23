package classes;

import delegatorsDefinitions.*;

import javax.swing.Icon;
import javax.swing.border.Border;

public class Frigate implements IVehicle, IMotorized, ISeaVehicle {
    private Vehicle vehicle;
    private Motorized motor;
    private SeaVehicle sea;

    public Frigate(String mod, int cap, int max, boolean wind, Icon pic) {
        vehicle = new Vehicle(mod, cap, max, pic);
        sea = new SeaVehicle(wind, "Israel");
        motor = new Motorized(500, 4);
    }

    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
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

    public String toString() {
        return vehicle.toString() + "\n" + sea.toString() + motor.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof Frigate)
            return (vehicle.equals(other) && sea.equals(other) && motor.equals(other));
        return false;
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
        Frigate tmp = new Frigate(vehicle.getModel(), vehicle.getCapacity(), vehicle.getMaxSpeed(), sea.getWind(), vehicle.getIcon());
        tmp.setMileage(vehicle.getMileage());
        tmp.setFlag(sea.getFlag());
        return tmp;
    }

    public void setMileage(int km) {
        vehicle.setMileage(km);
    }
}
