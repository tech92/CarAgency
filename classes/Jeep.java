package classes;

import delegatorsDefinitions.*;

import javax.swing.Icon;
import javax.swing.border.Border;

public class Jeep implements IVehicle, IMotorized, ICommercial, IGroundVehicle {
    private Vehicle vehicle;
    private Motorized motor;
    private Commercial comm;
    private GroundVehicle ground;

    public Jeep(String mod, int max, int lifetime, int fuel, Icon pic) {
        vehicle = new Vehicle(mod, 5, max, pic);
        ground = new GroundVehicle(4, "Dirt");
        motor = new Motorized(fuel, lifetime);
        comm = new Commercial("MINI");
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

    public String getLicenseType() {
        return comm.getLicenseType();
    }

    public String toString() {
        return vehicle.toString() + ground.toString() + "\n" + motor.toString() + comm.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof Jeep)
            return (vehicle.equals(other) && ground.equals(other) && motor.equals(other) && comm.equals(other));
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

    @Override
    public Object cloneVehicle() {
        Jeep tmp = new Jeep(vehicle.getModel(), vehicle.getMaxSpeed(), motor.getAverageMotorLifetime(), motor.getAverageFuelConsumption(), vehicle.getIcon());
        tmp.setMileage(vehicle.getMileage());
        return tmp;
    }

    public void setMileage(int km) {
        vehicle.setMileage(km);
    }
}
