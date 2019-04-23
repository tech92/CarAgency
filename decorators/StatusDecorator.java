package decorators;

import delegatorsDefinitions.IVehicle;

import javax.swing.border.Border;

public class StatusDecorator extends VehicleDecorator {
    private String status;
    private IVehicle vehicle;

    public StatusDecorator(IVehicle v, String s) {
        super(v);
        vehicle = v;
        status = s;
    }

    @Override
    public Object cloneVehicle() {
        return new StatusDecorator((IVehicle) vehicle.cloneVehicle(),status);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof StatusDecorator)
            return status.equals(((StatusDecorator) other).getStatus()) && vehicle.equals(((StatusDecorator) other).withoutStatusDecorator());
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " ,Status:" + status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public IVehicle withoutStatusDecorator() {
        return vehicle;
    }

    @Override
    public Border getBorder() {
        return vehicle.getBorder();
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }
}
