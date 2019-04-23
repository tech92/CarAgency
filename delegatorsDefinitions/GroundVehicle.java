package delegatorsDefinitions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GroundVehicle {
    private final int numberOfWheels;
    private final String terrainType;

    public GroundVehicle(int wheels, String type) {
        JLabel tmpLabel = new JLabel();
        if (wheels < 0) {
            JOptionPane.showMessageDialog(tmpLabel, "Wrong number of wheels was entered,0 was set by default.");
            wheels = 0;
        }
        numberOfWheels = wheels;
        if (!type.equals("Paved") && !type.equals("Dirt")) {
            JOptionPane.showMessageDialog(tmpLabel, "Wrong terrain type was entered,paved terrain was set by default.");
            type = "Paved";
        }
        terrainType = type;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public String getTerrainType() {
        return terrainType;
    }

    public String toString() {
        return " ,Number of wheels: " + String.valueOf(numberOfWheels) + " ,Terrain type: "
                + terrainType;
    }

    public boolean equals(Object other) {
        if (other instanceof IGroundVehicle)
            return (numberOfWheels == ((IGroundVehicle) other).getNumberOfWheels()
                    && terrainType.equals(((IGroundVehicle) other).getTerrainType()));
        return false;
    }
}
