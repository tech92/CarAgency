package classes;

import delegatorsDefinitions.*;

import javax.swing.Icon;

public class HybridAirplane extends Amphibian implements IAirVehicle {
    private AirVehicle air;


    public HybridAirplane(String mod, int cap, int max, int fuel, int life, boolean wind, String flag, int wheels, Icon pic) {
        super(mod, cap, max, wheels, wind, flag, fuel, life, pic);
        air = new AirVehicle("Military");
    }


    public void testdrive(int km) throws InterruptedException {
        super.testdrive(km);
    }

    public String toString() {
        return super.toString() + air.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof HybridAirplane)
            return super.equals(other) && air.equals(other);
        return false;
    }

    public String getUsage() {
        return air.getUsage();
    }

    public void setUsage(String use) {
        air.setUsage(use);
    }

    @Override
    public Object cloneVehicle() {
        HybridAirplane tmp = new HybridAirplane(super.getModel(), super.getCapacity(), super.getMaxSpeed(), super.getAverageFuelConsumption(), super.getAverageMotorLifetime(), super.getWind(), super.getFlag(), super.getNumberOfWheels(), super.getIcon());
        tmp.setMileage(super.getMileage());
        return tmp;
    }

    public void setMileage(int km) {
        super.setMileage(km);
    }

}
