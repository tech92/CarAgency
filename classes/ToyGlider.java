package classes;

import javax.swing.Icon;
import javax.swing.border.Border;

import delegatorsDefinitions.*;

public class ToyGlider implements IVehicle, INotMotorized, IAirVehicle {
    private Vehicle vehicle;
    private NotMotorized notMotor;
    private AirVehicle air;

    public ToyGlider(Icon pic) {
        vehicle = new Vehicle("Toy", 0, 10, pic);
        air = new AirVehicle("Civil");
        notMotor = new NotMotorized("Manual", "A");
    }

    public void testdrive(int km) throws InterruptedException {
        vehicle.testdrive(km);
    }

    public String getPowerSource() {
        return notMotor.getPowerSource();
    }

    public String getEnergeticScore() {
        return notMotor.getEnergeticScore();
    }

    public String toString() {
        return vehicle.toString() + air.toString() + notMotor.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof ToyGlider)
            return (vehicle.equals(other) && air.equals(other) && notMotor.equals(other));
        return false;
    }

    public String getUsage() {
        return air.getUsage();
    }

    public void setUsage(String use) {
        air.setUsage(use);
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
        ToyGlider tmp = new ToyGlider(vehicle.getIcon());
        tmp.setMileage(vehicle.getMileage());
        return tmp;
    }

    public void setMileage(int km) {
        vehicle.setMileage(km);
    }
}
