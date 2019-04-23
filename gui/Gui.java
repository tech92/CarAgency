package gui;


public class Gui {

    static void disableCloseButton() {
        MainWindow.disableCloseButton();
        AdditionalOptionsWindow.disableCloseButton();
        AddVehicleWindow.disableCloseButton();
        StockWindow.disableCloseButton();
    }

    static void enableCloseButton() {
        MainWindow.enableCloseButton();
        AdditionalOptionsWindow.enableCloseButton();
        AddVehicleWindow.enableCloseButton();
        StockWindow.enableCloseButton();
    }


    public Gui() {

        MainWindow.getInstance().setVisible(true);


    }

}
