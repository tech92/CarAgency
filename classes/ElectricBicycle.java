package classes;

import delegatorsDefinitions.IMotorized;
import delegatorsDefinitions.Motorized;

import javax.swing.Icon;

public class ElectricBicycle extends BicycleTemplate implements IMotorized {
    private Motorized motor;

    public ElectricBicycle(String mod, int cap, int max, int life, String type, Icon pic) {
        super(mod, cap, max, type, pic, 2);
        motor = new Motorized(2, life);
    }

    public String toString() {

        return super.toString() + "\n" + motor.toString();
    }


    public boolean equals(Object other) {
        if (other instanceof ElectricBicycle)
            return super.equals(other) && motor.equals(other);
        return false;
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
    public Object cloneVehicle() {
        ElectricBicycle tmp = new ElectricBicycle(super.getModel(), super.getCapacity(), super.getMaxSpeed(), motor.getAverageMotorLifetime(), super.getTerrainType(), super.getIcon());
        tmp.setMileage(super.getMileage());
        return tmp;
    }

    public void setMileage(int km) {
        super.setMileage(km);
    }
}
