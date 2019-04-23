package delegatorsDefinitions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AirVehicle {
    private String usage;

    public AirVehicle(String use) {
        if (!use.equals("Military") && !use.equals("Civil")) {
            JLabel tmpLabel = new JLabel();
            JOptionPane.showMessageDialog(tmpLabel, "Wrong use was entered,civil use was set by default.");
            use = "Civil";
        }
        usage = use;
    }



    public String getUsage() {
        return usage;
    }


    public void setUsage(String use) {
        if (!use.equals("Military") && !use.equals("Civil")) {
            System.out.println("\nWrong use was entered,civil use was set by default.\n");
            use = "Civil";
        }
        usage = use;
    }

    public String toString() {
        return ", Usage: " + usage;
    }

    public boolean equals(Object other) {
        if (other instanceof IAirVehicle)
            return usage.equals(((IAirVehicle) other).getUsage());
        return false;
    }
}
