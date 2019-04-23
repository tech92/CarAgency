package delegatorsDefinitions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class NotMotorized implements INotMotorized {
    private final String powerSource;
    private final String energeticScore;

    public NotMotorized(String source, String score) {
        powerSource = source;
        JLabel tmpLabel = new JLabel();
        if (!score.equals("A") && !score.equals("B") && !score.equals("C")) {
            JOptionPane.showMessageDialog(tmpLabel, "Wrong energetic score was entered ,A was set by default.");
            score = "A";
        }
        energeticScore = score;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public String getEnergeticScore() {
        return energeticScore;
    }

    public String toString() {
        return " ,Power source: " + powerSource + " ,Energetic score: " + String.valueOf(energeticScore);
    }

    public boolean equals(Object other) {
        if (other instanceof INotMotorized)
            return (powerSource.equals(((INotMotorized) other).getPowerSource())
                    && energeticScore.equals(((INotMotorized) other).getEnergeticScore()));
        return false;
    }
}
