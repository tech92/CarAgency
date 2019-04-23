package delegatorsDefinitions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Commercial implements ICommercial {
    private final String licenseType;

    public Commercial(String type) {
        if (!type.equals("MINI") && !type.equals("LIMITED") && !type.equals("UNLIMITED")) {
            JLabel tmpLabel = new JLabel();
            JOptionPane.showMessageDialog(tmpLabel, "Wrong license type was entered, MINI was set by default.");
            type = "MINI";
        }
        licenseType = type;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public String toString() {
        return " ,License type: " + licenseType;
    }

    public boolean equals(Object other) {
        if (other instanceof ICommercial)
            return (licenseType.equals(((ICommercial) other).getLicenseType()));
        return false;
    }
}
