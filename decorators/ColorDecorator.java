package decorators;

import delegatorsDefinitions.IVehicle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ColorDecorator extends VehicleDecorator {
    private String color;
    private IVehicle vehicle;

    public ColorDecorator(IVehicle v, String c) {
        super(v);
        vehicle = v;
        color = c;
    }

    @Override
    public Object cloneVehicle() {
        return new ColorDecorator((IVehicle)vehicle.cloneVehicle(),color);
    }

    public IVehicle withoutColorDecorator() {
        return vehicle;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ColorDecorator)
            return color.equals(((ColorDecorator) other).getColor()) && vehicle.equals(((ColorDecorator) other).withoutColorDecorator());
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "\n,Color: " + color;
    }

    @Override
    public String getColor() {
        return vehicle.getColor()+color;
    }

    @Override
    public Border getBorder() {
        switch (color) {
            case "Black":
                return BorderFactory.createLineBorder(Color.BLACK, 5);
            case "Red":
                return BorderFactory.createLineBorder(Color.RED, 5);
            case "Yellow":
                return BorderFactory.createLineBorder(Color.YELLOW, 5);
            case "Blue":
                return BorderFactory.createLineBorder(Color.BLUE, 5);
            case "Purple":
                return BorderFactory.createLineBorder(Color.MAGENTA, 5);
            case "Gray":
                return BorderFactory.createLineBorder(Color.GRAY, 5);
            default: // green
                return BorderFactory.createLineBorder(Color.GREEN, 5);
        }
    }
}
