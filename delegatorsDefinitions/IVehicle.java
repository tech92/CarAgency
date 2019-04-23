package delegatorsDefinitions;

import javax.swing.Icon;
import javax.swing.border.Border;

public interface IVehicle {


    String getColor();

    Border getBorder();

    String getStatus();

    void testdrive(int km) throws InterruptedException;

    Icon getIcon();

    int getMileage();

    String getModel();

    int getCapacity();

    int getMaxSpeed();

    void resetMileage();

    Object cloneVehicle();

    void setMileage(int km);

}
