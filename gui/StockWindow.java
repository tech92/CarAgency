package gui;


import delegatorsDefinitions.IVehicle;
import delegatorsDefinitions.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

class StockWindow extends JFrame {

    private static Vector<StockWindow> instances = new Vector<>();

    static void disableCloseButton() {
        for (StockWindow sw : instances)
            sw.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    static void enableCloseButton() {
        for (StockWindow sw : instances)
            sw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    static void callbackVehicleAdded(IVehicle v) {
        for (StockWindow sw : instances) {
            sw.addNewVehicle(v);
        }
    }

    static void callbackVehicleRemoved(int index) {
        for (StockWindow sw : instances) {
            sw.removeLabel(index);
        }
    }

    static void callbackVehicleUpdated(int index,IVehicle vehicle) {
        for (StockWindow sw : instances) {
            sw.updateLabelsVehicle(index,vehicle);
        }
    }

    static void disposeAll() {
        for (StockWindow sw:instances)
            sw.dispose();
    }

    private JPanel listPanel = new JPanel();
    private TextArea textArea = new TextArea();
    private Dimension listPanelDimension = new Dimension(650, 300);
    private Vector<JLabel> labelsByIndexes = new Vector<>();
    private int labelsCounter = 0;

    private void setLabelsVehicle(JLabel label,IVehicle vehicle) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBorder(vehicle.getBorder());
                textArea.setText(vehicle.toString());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBorder(BorderFactory.createEmptyBorder());
                textArea.setText("");
            }
        });
    }
    private void updateLabelsVehicle(int index,IVehicle vehicle) {
        setLabelsVehicle(labelsByIndexes.get(index),vehicle);
    }

    private void removeLabel(int index) {
        listPanel.remove(labelsByIndexes.get(index));
        listPanel.validate();
        listPanel.repaint();
        labelsByIndexes.remove(index);
        labelsCounter--;
        if (labelsCounter == 0) // if row is empty , remove the empty row
            listPanelDimension.setSize((double) 650, listPanelDimension.getHeight() - 200);
    }

    private void addNewVehicle(IVehicle v) {
        JLabel tmpLabel = new JLabel();
        tmpLabel.setIcon(v.getIcon());
        if (labelsCounter == 3) { // the row is full , add an empty row before adding a label
            listPanelDimension.setSize((double) 650, listPanelDimension.getHeight() + 200);
            labelsCounter = 0; // the row is empty
        }
        labelsByIndexes.add(tmpLabel);
        listPanel.add(tmpLabel);
        listPanel.validate();
        listPanel.repaint();
        setLabelsVehicle(tmpLabel,v);
        labelsCounter++;

    }

    StockWindow() {
        super("Stock");
        setBounds(500, 200, 700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        textArea.setEditable(false);

        JLabel textLabel = new JLabel("Hover over a picture to see its information:");

        textArea.setPreferredSize(new Dimension(675, 50));


        listPanel.setPreferredSize(listPanelDimension);
        JScrollPane listScroll = new JScrollPane(listPanel);
        listScroll.setPreferredSize(new Dimension(675, 500));

        int vehiclesSize = Vehicle.getSize();
        for (int i = 0; i < vehiclesSize; i++)
            addNewVehicle(Vehicle.getVehicle(i));

        add(textLabel);
        add(textArea);
        add(listScroll);
        setVisible(true);
        instances.add(this);
    }
}
