package gui;


import Memento.Caretaker;
import Memento.Originator;
import delegatorsDefinitions.ISeaVehicle;
import delegatorsDefinitions.IVehicle;
import delegatorsDefinitions.SeaVehicle;
import delegatorsDefinitions.Vehicle;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;


class MainWindow extends JFrame implements MileageObserver { // Singleton

    private static volatile MainWindow instance;

    static MainWindow getInstance() {
        if (instance == null)
            synchronized (MainWindow.class) {  // DCL
                if (instance == null)
                    instance = new MainWindow();
            }
        return instance;
    }

    static void disableCloseButton() {
        if (instance != null)
            instance.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    static void enableCloseButton() {
        if (instance != null)
            instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private int totalMileage = 0;
    private JLabel mileageLabel = new JLabel(String.valueOf(totalMileage));

    @Override
    public void notifyUpdateMileage(int km) {
        totalMileage += km;
        mileageLabel.setText(String.valueOf(totalMileage));
    }


    private void disposeAllWindowsExceptMain() {
        AdditionalOptionsWindow.disposeAll();
        AddVehicleWindow.disposeAll();
        StockWindow.disposeAll();
    }

    private Caretaker caretaker = new Caretaker();

    private Originator originator = new Originator();

    private MainWindow() {
        super("Welcome to the main menu");
        setBounds(500, 200, 700, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        addWindowListener(new WindowListener() {
            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                AdditionalOptionsWindow.shutdownPool();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }
        });
        JLabel mileageTextLabel = new JLabel("Total mileage: ");
        mileageTextLabel.setSize(100, 20);
        add(mileageTextLabel);
        JButton createMementoButton = new JButton("Save current state");
        createMementoButton.setSize(150, 20);
        createMementoButton.setLocation(370, 0);
        createMementoButton.addActionListener(l -> {
            Vector<IVehicle> tmpVehicleList = new Vector<>();
            Vector<ISeaVehicle> tmpSeaList = new Vector<>();
            Vehicle.cloneList(tmpVehicleList, tmpSeaList);
            originator.setVehicleList(tmpVehicleList); // setStatus
            originator.setSeaList(tmpSeaList);         // setStatus
            originator.setTotalMileage(totalMileage);  // setStatus
            caretaker.addMemento(originator.createMemento());
            JOptionPane.showMessageDialog(null, "Current state was saved");
        });
        add(createMementoButton);
        JButton backMementoButton = new JButton("Back to previous state");
        backMementoButton.setSize(170, 20);
        backMementoButton.setLocation(520, 0);
        backMementoButton.addActionListener(l -> {
            if (originator.setMemento(caretaker.getMemento())) { // if there is a state saved
                disposeAllWindowsExceptMain();
                Vehicle.setVehicleList(originator.getVehicleList());
                SeaVehicle.setSeaList(originator.getSeaList());
                int mileage = originator.getTotalMileage();
                mileageLabel.setText(String.valueOf(mileage));
                totalMileage = mileage;
                JOptionPane.showMessageDialog(null, "The system is back to the previous state");
            } else
                JOptionPane.showMessageDialog(null, "First you need to save a state");
        });
        add(backMementoButton);
        mileageLabel.setSize(100, 20);
        mileageLabel.setLocation(105, 0);
        add(mileageLabel);
        JButton addVehiclesButton = new JButton();
        JButton additionalOptionsButton = new JButton();
        JButton stockButton = new JButton();
        addVehiclesButton.setSize(500, 100);
        addVehiclesButton.setLocation(100, 100);
        addVehiclesButton.setIcon(new ImageIcon(getClass().getResource("pics/new.png")));
        add(addVehiclesButton);
        additionalOptionsButton.setSize(500, 100);
        additionalOptionsButton.setLocation(100, 250);
        additionalOptionsButton.setIcon(new ImageIcon(getClass().getResource("pics/additional.png")));
        add(additionalOptionsButton);
        stockButton.setSize(500, 100);
        stockButton.setLocation(100, 400);
        stockButton.setIcon(new ImageIcon(getClass().getResource("pics/stock.png")));
        add(stockButton);

        addVehiclesButton.addActionListener(l -> new AddVehicleWindow());

        additionalOptionsButton.addActionListener(l -> new AdditionalOptionsWindow().registerObserver(this));

        stockButton.addActionListener(l -> new StockWindow());
    }

}
