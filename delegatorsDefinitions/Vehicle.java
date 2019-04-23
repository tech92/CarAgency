package delegatorsDefinitions;


import decorators.ColorDecorator;
import decorators.StatusDecorator;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import java.util.Vector;


public class Vehicle {

    private static Vector<IVehicle> vehicleList = new Vector<>();

    public static void setVehicleList(Vector<IVehicle> list) {
        vehicleList = list;
    }

    public static void cloneList(Vector<IVehicle> cloneVehicleList,Vector<ISeaVehicle> cloneSeaList) {
        for (IVehicle v : vehicleList) {
            IVehicle tmpVehicle = (IVehicle) (v.cloneVehicle());
            cloneVehicleList.add(tmpVehicle);
            try { // in case the is a SeaVehicle
                cloneSeaList.add((ISeaVehicle)((ColorDecorator)((StatusDecorator) tmpVehicle).withoutStatusDecorator()).withoutColorDecorator());
            } catch (ClassCastException c){

            }
        }

    }

    public static void addVehicle(IVehicle v) {
        vehicleList.add(v);
    }

    public static IVehicle getVehicle(int index) {
        return vehicleList.get(index);
    }

    public static int getSize() {
        return vehicleList.size();
    }

    public static boolean contains(IVehicle v) {
        return vehicleList.contains(v);
    }

    public static void removeVehicle(IVehicle v) {
        vehicleList.remove(v);
    }

    public static int getIndex(IVehicle v) {
        return vehicleList.indexOf(v);
    }

    public static void set(int i, IVehicle v) {
        vehicleList.set(i, v);
    }

    private int mileage;
    private final String model;
    private final int capacity;
    private final int maxSpeed;
    private final Icon picture;

    public Vehicle(String mod, int cap, int max, Icon pic) {
        JLabel tmpLabel = new JLabel();
        if (cap < 0) {
            JOptionPane.showMessageDialog(tmpLabel, "Wrong capacity was entered,0 was set by default.");
            cap = 0;
        }
        if (max < 0) {
            JOptionPane.showMessageDialog(tmpLabel, "Wrong maximum speed was entered,0 was set by default.");
            max = 0;
        }
        mileage = 0;
        model = mod;
        capacity = cap;
        maxSpeed = max;
        picture = pic;
    }

    public String getColor() {
        return "";
    }

    public Border getBorder() {
        return null;
    }

    public String getStatus() {
        return "";
    }

    public void testdrive(int km) throws InterruptedException {
        if (km < 0) {
            JOptionPane.showMessageDialog(null, "Wrong mileage was entered");
            return;
        }
        Thread.sleep(km * 100);
        mileage += km;
    }

    public Icon getIcon() {
        return picture;
    }

    public int getMileage() {
        return mileage;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String toString() {
        return "Model: " + model + " ,Capacity: " + String.valueOf(capacity) + " ,Max speed: "
                + String.valueOf(maxSpeed) + " ,Mileage: " + String.valueOf(mileage);
    }

    public boolean equals(Object other) {
        if (other instanceof IVehicle)
            return (mileage == ((IVehicle) other).getMileage() && model.equals(((IVehicle) other).getModel())
                    && capacity == ((IVehicle) other).getCapacity() && maxSpeed == ((IVehicle) other).getMaxSpeed());
        return false;
    }

    public void resetMileage() {
        mileage = 0;
    }

    public void setMileage(int km) {
        mileage = km;
    }

}
