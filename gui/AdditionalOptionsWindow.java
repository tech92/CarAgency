package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;


import javax.swing.*;
import javax.swing.border.Border;

import decorators.ColorDecorator;
import decorators.StatusDecorator;
import delegatorsDefinitions.ISeaVehicle;
import delegatorsDefinitions.IVehicle;
import delegatorsDefinitions.SeaVehicle;
import delegatorsDefinitions.Vehicle;


class AdditionalOptionsWindow extends JFrame {

    private JLabel currentClickedLabel = null;

    private MileageObserver mileageObserver = null;

    void registerObserver(MileageObserver observer) {
        mileageObserver = observer;
    }

    private static final int MAX_THREADS = 7;

    private static final ExecutorService testdrivePool = Executors.newFixedThreadPool(MAX_THREADS);

    static void shutdownPool() {
        testdrivePool.shutdown();
    }

    private static Vector<AdditionalOptionsWindow> instances = new Vector<>();

    static void disableCloseButton() {
        for (AdditionalOptionsWindow ao : instances)
            ao.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    static void enableCloseButton() {
        for (AdditionalOptionsWindow ao : instances)
            ao.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    static void callbackVehicleAdded(IVehicle v) {
        for (AdditionalOptionsWindow aow : instances)
            aow.addNewVehicle(v);
    }

    private static void callbackRemoveLabel(int index) {
        for (AdditionalOptionsWindow aow : instances)
            aow.removeLabel(index);
    }

    private static void callbackUpdateToString(int index, IVehicle v) {
        for (AdditionalOptionsWindow aow : instances)
            aow.updateToString(index, v);
    }

    private static void callbackUpdateAllToString() {
        for (AdditionalOptionsWindow aow : instances)
            aow.updateAllToString();
    }


    private void removeLabel(int index) {
        leftPanel.remove(index);
        leftPanel.validate();
        leftPanel.repaint();
        labelsByIndexes.remove(index);
        leftPanelDimension.setSize((double) 200, leftPanelDimension.getHeight() - 200);
        currentClickedLabel = null;
    }


    private void addNewVehicle(IVehicle v) {
        JLabel tmpLabel = new JLabel();
        Icon icon = v.getIcon();
        tmpLabel.setIcon(icon);
        labelsByIndexes.add(tmpLabel);
        leftPanel.add(tmpLabel);
        leftPanel.validate();
        leftPanel.repaint();
        tmpLabel.setToolTipText(v.toString());
        tmpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (currentClickedLabel == null) { // no label is selected right now
                    tmpLabel.setBorder(v.getBorder());
                    currentClickedLabel = tmpLabel;
                } else {
                    currentClickedLabel.setBorder(emptyBorder);
                    currentClickedLabel = tmpLabel;
                    tmpLabel.setBorder(v.getBorder());
                }
            }
        });
        leftPanelDimension.setSize((double) 200, leftPanelDimension.getHeight() + 200);
    }

    private void updateToString(int index, IVehicle v) {
        JLabel tmpLabel = labelsByIndexes.get(index);
        tmpLabel.setToolTipText(v.toString());
    }

    private void updateAllToString() {
        int size = labelsByIndexes.size();
        for (int i = 0; i < size; i++)
            labelsByIndexes.get(i).setToolTipText(Vehicle.getVehicle(i).toString());

    }

    static void disposeAll() {
        for (AdditionalOptionsWindow aow : instances)
            aow.dispose();
    }


    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_left_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private JPanel leftPanel = new JPanel();
    private Dimension leftPanelDimension = new Dimension(200, 300);
    private Vector<JLabel> labelsByIndexes = new Vector<>();
    private static Border emptyBorder = BorderFactory.createEmptyBorder();


    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_AdditionalOptionsWindow_constructor_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    AdditionalOptionsWindow() {
        super("Additional options");
        setBounds(500, 200, 700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_top_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel topPanel = new JPanel();
        FlowLayout topLayout = new FlowLayout();
        topPanel.setLayout(topLayout);
        topLayout.setAlignment(FlowLayout.LEFT);
        add(topPanel, BorderLayout.PAGE_START);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_left_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JScrollPane leftPanelScroll = new JScrollPane(leftPanel);
        leftPanel.setPreferredSize(leftPanelDimension);
        leftPanelScroll.setPreferredSize(new Dimension(230, 600));
        int vehiclesSize = Vehicle.getSize();
        for (int i = 0; i < vehiclesSize; i++)
            addNewVehicle(Vehicle.getVehicle(i));
        add(leftPanelScroll, BorderLayout.LINE_START);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_center_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel();
        JButton buyButton = new JButton("Buy");
        JButton testdriveButton = new JButton("Take to a testdrive");
        JButton resetButton = new JButton("Reset all the mileages");
        JButton flagsButton = new JButton("Change all the flags");
        JButton confirmFlagsButton = new JButton("Confirm");
        Icon[] flags = {
                new ImageIcon(getClass().getResource("pics/israel.png")),
                new ImageIcon(getClass().getResource("pics/us.png")),
                new ImageIcon(getClass().getResource("pics/germany.png")),
                new ImageIcon(getClass().getResource("pics/italy.png")),
                new ImageIcon(getClass().getResource("pics/greece.png")),
                new ImageIcon(getClass().getResource("pics/somalia.png")),
                new ImageIcon(getClass().getResource("pics/pirate.png"))
        };
        JComboBox<Icon> flagsComboBox = new JComboBox<>(flags);
        String[] flagsTexts = {"Israel", "United States", "Germany", "Italy", "Greece", "Somalia", "Pirate"};

        centerPanel.setLayout(new GridLayout(4, 2, 15, 15));
        centerPanel.add(new JLabel("<<< Choose a vehicle and an option:"));
        centerPanel.add(new JLabel());
        centerPanel.add(buyButton);
        centerPanel.add(testdriveButton);
        centerPanel.add(resetButton);
        centerPanel.add(flagsButton);
        flagsComboBox.setVisible(false);
        confirmFlagsButton.setVisible(false);
        centerPanel.add(flagsComboBox);
        centerPanel.add(confirmFlagsButton);

        // --------------_buttons_actionsListeners_--------------

        buyButton.addActionListener(l -> {
            if (currentClickedLabel != null) { // user chose a vehicle
                int i = labelsByIndexes.indexOf(currentClickedLabel);
                IVehicle tmpVehicle = Vehicle.getVehicle(i);
                if (tmpVehicle.getStatus().equals("Available")) {
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {

                            synchronized (tmpVehicle) { // almost impossible but a time-slice could end right after the "if" and few threads could enter
                                if (Vehicle.contains(tmpVehicle)) { // maybe someone bought the vehicle while we were waiting
                                    try {
                                        Gui.disableCloseButton(); // user shouldn't be able to exit the system
                                        JOptionPane.showMessageDialog(buyButton, "Think for a few seconds..\n* The vehicle won't be available at this time.\n* Also you can't close the system during this procedure.");
                                        ((StatusDecorator) tmpVehicle).setStatus("In buying process");
                                        int index = Vehicle.getIndex(tmpVehicle); // index could change while waiting
                                        AdditionalOptionsWindow.callbackUpdateToString(index, tmpVehicle); // update label's toolTipText
                                        StockWindow.callbackVehicleUpdated(index, tmpVehicle); // relate the label to the "new" vehicle
                                        Thread.sleep((new Random().nextInt(6) + 5) * 1000); // from 5 to 10 seconds
                                        if (JOptionPane.showConfirmDialog(buyButton, "Do you still want to buy the vehicle?") == JOptionPane.YES_OPTION) {
                                            synchronized (UpdatingWindow.lock) { // only one procedure can update the system at the same time
                                                new UpdatingWindow();
                                                int i = Vehicle.getIndex(tmpVehicle); // the index of the label could change while system was updating
                                                AdditionalOptionsWindow.callbackRemoveLabel(i);
                                                StockWindow.callbackVehicleRemoved(i);
                                                Vehicle.removeVehicle(tmpVehicle);
                                                try { // in case this is a SeaVehicle , must remove layers (they can't be an instance of ISeaVehicle)
                                                    SeaVehicle.removeSeaVehicle((ISeaVehicle) ((ColorDecorator) ((StatusDecorator) tmpVehicle).withoutStatusDecorator()).withoutColorDecorator());
                                                } catch (ClassCastException c) {
                                                    //empty
                                                }
                                                if (mileageObserver != null)
                                                    mileageObserver.notifyUpdateMileage(-tmpVehicle.getMileage());
                                            }
                                            JOptionPane.showMessageDialog(buyButton, "You have bought the vehicle!");
                                        } else { // answer != YES
                                            ((StatusDecorator) tmpVehicle).setStatus("Available"); // replace the status decorator
                                            AdditionalOptionsWindow.callbackUpdateToString(index, tmpVehicle); // update label's toolTipText
                                            StockWindow.callbackVehicleUpdated(index, tmpVehicle); // relate the label to the "new" vehicle
                                        }
                                        Gui.enableCloseButton();

                                    } catch (InterruptedException e) {
                                        JOptionPane.showMessageDialog(buyButton, "Thinking was interrupted.");
                                        Gui.enableCloseButton();
                                    }
                                } else
                                    JOptionPane.showMessageDialog(buyButton, "Sorry,the vehicle was bought while you were waiting.");
                            }

                            return null;
                        }
                    }.execute();
                } else
                    JOptionPane.showMessageDialog(buyButton, "Sorry,the vehicle is not available right now.");
                currentClickedLabel.setBorder(emptyBorder);
                currentClickedLabel = null;
            } else
                JOptionPane.showMessageDialog(buyButton, "Please select a vehicle first.");

        });


        testdriveButton.addActionListener(l ->
        {
            if (currentClickedLabel != null) {

                int i = labelsByIndexes.indexOf(currentClickedLabel);
                IVehicle tmpVehicle = Vehicle.getVehicle(i);
                if (tmpVehicle.getStatus().equals("Available")) {
                    testdrivePool.execute(new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            synchronized (tmpVehicle) { // almost impossible but a time-slice could end right after the "if" and few threads could enter
                                if (Vehicle.contains(tmpVehicle)) { // maybe someone bought the vehicle while we were waiting
                                    try {
                                        Gui.disableCloseButton(); // user shouldn't be able to close exit the system
                                        int length = Integer.parseInt(
                                                JOptionPane.showInputDialog(testdriveButton, "Enter the length of the testdrive: "));
                                        if (length < 1) {
                                            JOptionPane.showMessageDialog(testdriveButton, "Wrong input.");
                                            throw new NumberFormatException();
                                        }
                                        JOptionPane.showMessageDialog(testdriveButton, "Enjoy your " + String.valueOf(length / 10.0) + " second(s) long testdrive.\n* The vehicle won't be available at this time.\n* Only " + String.valueOf(MAX_THREADS) + " vehicle(s) can be on a testdrive at the same time.\n* Also you can't close the system during the testdrive.");
                                        IVehicle v = new StatusDecorator(((StatusDecorator) tmpVehicle).withoutStatusDecorator(), "On a testdrive"); // replace the status decorator
                                        int index = Vehicle.getIndex(tmpVehicle);
                                        Vehicle.set(index, v); // replace the vehicle with the "new" one ( after adding the decoration layer )
                                        AdditionalOptionsWindow.callbackUpdateToString(index, v); // update label's toolTipText
                                        StockWindow.callbackVehicleUpdated(index, v); // relate the label to the "new" vehicle
                                        v.testdrive(length);
                                        v = new StatusDecorator(((StatusDecorator) v).withoutStatusDecorator(), "Available"); // replace the status decorator
                                        Vehicle.set(index, v); // replace the vehicle with the "new" one ( after adding the decoration layer )
                                        AdditionalOptionsWindow.callbackUpdateToString(index, v); // update label's toolTipText
                                        StockWindow.callbackVehicleUpdated(index, v); // relate the label to the "new" vehicle
                                        if (mileageObserver != null)
                                            mileageObserver.notifyUpdateMileage(length);
                                        JOptionPane.showMessageDialog(testdriveButton, "Testdrive ended.");
                                        Gui.enableCloseButton();
                                    } catch (NumberFormatException e) { // no testdrive length was entered or length < 1
                                        Gui.enableCloseButton();
                                    } catch (InterruptedException e) {
                                        JOptionPane.showMessageDialog(testdriveButton, "The testdrive was interrupted.");
                                        Gui.enableCloseButton();
                                    }
                                } else
                                    JOptionPane.showMessageDialog(buyButton, "Sorry,the vehicle was bought while you were waiting.");
                            }

                            return null;
                        }
                    });
                } else
                    JOptionPane.showMessageDialog(buyButton, "Sorry,the vehicle is not available right now.");
                currentClickedLabel.setBorder(emptyBorder);
                currentClickedLabel = null;


            } else
                JOptionPane.showMessageDialog(testdriveButton, "Please select a vehicle first.");

        });

        resetButton.addActionListener(l -> {
            int size = labelsByIndexes.size();
            if (size != 0) {
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        synchronized (UpdatingWindow.lock) { // only one procedure can update the system at the same time
                            new UpdatingWindow(); //  3 to 8 seconds waiting
                            for (int i = 0; i < size; i++) {
                                IVehicle v = Vehicle.getVehicle(i);
                                if (v.getStatus().equals("Available")) {
                                    if (mileageObserver != null)
                                        mileageObserver.notifyUpdateMileage(-v.getMileage());
                                    v.resetMileage();
                                }
                            }
                            AdditionalOptionsWindow.callbackUpdateAllToString();
                        }
                        JOptionPane.showMessageDialog(resetButton, "The mileage of all the vehicles was reset.\n(Except for the ones that wasn't available)");
                        return null;
                    }
                }.execute();
            } else
                JOptionPane.showMessageDialog(resetButton, "Please add a vehicle first.");
        });

        flagsButton.addActionListener(l ->

        {
            if (SeaVehicle.getSize() != 0) {
                flagsComboBox.setVisible(!flagsComboBox.isVisible());
                confirmFlagsButton.setVisible(!confirmFlagsButton.isVisible());
            } else
                JOptionPane.showMessageDialog(resetButton, "Please add a sea-vehicle first.");
        });

        confirmFlagsButton.addActionListener(l ->

        {
            int size = SeaVehicle.getSize();
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    String flagText = flagsTexts[flagsComboBox.getSelectedIndex()];
                    synchronized (UpdatingWindow.lock) { // only one procedure can update the system at the same time
                        new UpdatingWindow(); //  3 to 8 seconds waiting
                        for (int i = 0; i < size; i++)
                            SeaVehicle.getSeaVehicle(i).setFlag(flagText);
                        AdditionalOptionsWindow.callbackUpdateAllToString();
                        flagsComboBox.setVisible(false);
                        confirmFlagsButton.setVisible(false);
                    }
                    JOptionPane.showMessageDialog(resetButton, "The flag of all the sea vehicles was changed to \"" + flagText + "\" flag.");
                    return null;
                }
            }.execute();
        });

        // --------------_buttons_actionsListeners_end_--------------

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
        instances.add(this);
    }

}
