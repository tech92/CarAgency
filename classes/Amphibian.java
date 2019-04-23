package classes;

import javax.swing.*;
import javax.swing.border.Border;

import delegatorsDefinitions.*;

public class Amphibian implements IVehicle, IMotorized, IGroundVehicle, ISeaVehicle {
    private Vehicle vehicle;
    private Motorized motor;
    private GroundVehicle ground;
    private SeaVehicle sea;

    public Amphibian(String mod, int cap, int max, int wheels, boolean wind, String flag, int fuel, int lifetime, Icon pic) {
        vehicle = new Vehicle(mod, cap, max, pic);
        ground = new GroundVehicle(wheels, "Paved");
        sea = new SeaVehicle(wind, flag);
        motor = new Motorized(fuel, lifetime);
    }


    @Override
    public Object cloneVehicle() {
        Amphibian tmp = new Amphibian(vehicle.getModel(), vehicle.getCapacity(), vehicle.getMaxSpeed(), ground.getNumberOfWheels(), sea.getWind(), sea.getFlag(), motor.getAverageFuelConsumption(), motor.getAverageMotorLifetime(), vehicle.getIcon());
        tmp.setMileage(vehicle.getMileage());
        return tmp;
    }

    @Override
    public String getColor() {
        return vehicle.getColor();
    }

    @Override
    public String getStatus() {
        return vehicle.getStatus();
    }

    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
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

    public String toString() {
        return vehicle.toString() + ground.toString() + "\n" + sea.toString() + motor.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof Amphibian)
            return (vehicle.equals(other) && ground.equals(other) && sea.equals(other) && motor.equals(other));
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


    public int getNumberOfWheels() {
        return ground.getNumberOfWheels();
    }


    public String getTerrainType() {
        return ground.getTerrainType();
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

    public void setMileage(int km) {
        vehicle.setMileage(km);
    }

    @Override
    public Border getBorder() {
        return vehicle.getBorder();
    }
}
