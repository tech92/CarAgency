package classes;

import delegatorsDefinitions.NotMotorized;

import javax.swing.Icon;

import delegatorsDefinitions.INotMotorized;

public class Bicycle extends BicycleTemplate implements INotMotorized {
    private NotMotorized notMotor;


    public Bicycle(String mod, int cap, int max, String type, Icon pic) {
        super(mod, cap, max, type, pic, 2);
        notMotor = new NotMotorized("Manual", "A");
    }

    public String getPowerSource() {

        return notMotor.getPowerSource();
    }

    public String getEnergeticScore() {
        return notMotor.getEnergeticScore();
    }

    public String toString() {

        return super.toString() + "\n" + notMotor.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof Bicycle)
            return (super.equals(other) && notMotor.equals(other));
        return false;
    }

    @Override
    public Object cloneVehicle() {
        Bicycle tmp = new Bicycle(super.getModel(), super.getCapacity(), super.getMaxSpeed(), super.getTerrainType(), super.getIcon());
        tmp.setMileage(super.getMileage());
        return tmp;
    }

    public void setMileage(int km) {super.setMileage(km);}
}
