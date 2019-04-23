package delegatorsDefinitions;

import java.util.Vector;

public class SeaVehicle {

    private static Vector<ISeaVehicle> seaList = new Vector<>();

    public static void setSeaList(Vector<ISeaVehicle> list) {
        seaList = list;
    }

    public static int getSize() {
        return seaList.size();
    }

    public static void addSeaVehicle(ISeaVehicle s) {
        seaList.add(s);
    }

    public static void removeSeaVehicle(ISeaVehicle s) {
        seaList.remove(s);
    }

    public static ISeaVehicle getSeaVehicle(int index) {
        return seaList.get(index);
    }

    private boolean goesWithTheWind;
    private String countryFlag;

    public SeaVehicle(boolean wind, String flag) {
        goesWithTheWind = wind;
        countryFlag = flag;
    }

    public boolean getWind() {
        return goesWithTheWind;
    }

    public String getFlag() {
        return countryFlag;
    }

    public void setWind(boolean wind) {
        goesWithTheWind = wind;
    }

    public void setFlag(String flag) {
        countryFlag = flag;
    }

    public String toString() {
        String wind;
        if (goesWithTheWind)
            wind = "Yes";
        else
            wind = "No";
        return " ,Goes with the wind: " + wind + " ,Flag: " + countryFlag;
    }

    public boolean equals(Object other) {
        if (other instanceof ISeaVehicle)
            return (goesWithTheWind == ((ISeaVehicle) other).getWind()
                    && countryFlag.equals(((ISeaVehicle) other).getFlag()));
        return false;
    }

}
