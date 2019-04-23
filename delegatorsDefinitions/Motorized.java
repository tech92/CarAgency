package delegatorsDefinitions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Motorized implements IMotorized {
	private int averageFuelConsumption;
	private final int averageMotorLifetime;

	public Motorized(int fuelConsumption, int lifetime) {
		JLabel tmpLabel = new JLabel();
		if (fuelConsumption < 0) {
			JOptionPane.showMessageDialog(tmpLabel, "Wrong average fuel consumption was entered,0 was set by default.");
			fuelConsumption = 0;
		}
		averageFuelConsumption = fuelConsumption;
		if (lifetime < 0) {
			JOptionPane.showMessageDialog(tmpLabel, "Wrong average motor lifetime was entered,0 was set by default.");
			lifetime = 0;
		}
		averageMotorLifetime = lifetime;
	}

	public int getAverageFuelConsumption() {
		return averageFuelConsumption;
	}

	public void setAverageFuelConsumption(int fuelConsumption) {
		if (fuelConsumption < 0) {
			System.out.println("Wrong average fuel consumption was entered,0 was set by default.");
			fuelConsumption = 0;
		}
		averageFuelConsumption = fuelConsumption;
	}

	public int getAverageMotorLifetime() {
		return averageMotorLifetime;
	}

	public String toString() {
		return " ,Average fuel consumption: " + String.valueOf(averageFuelConsumption) + " ,Average motor lifetime: "
				+ String.valueOf(averageMotorLifetime);
	}

	public boolean equals(Object other) {
		if (other instanceof IMotorized)
			return (averageFuelConsumption == ((IMotorized) other).getAverageFuelConsumption()
					&& averageMotorLifetime == ((IMotorized) other).getAverageMotorLifetime());
		return false;
	}
}
