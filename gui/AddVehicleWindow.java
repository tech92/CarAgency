package gui;

import java.awt.*;
import java.io.File;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import carFactory.GeneralFactory;
import decorators.ColorDecorator;
import decorators.StatusDecorator;
import delegatorsDefinitions.ISeaVehicle;
import delegatorsDefinitions.IVehicle;
import delegatorsDefinitions.SeaVehicle;
import delegatorsDefinitions.Vehicle;

public class AddVehicleWindow extends JFrame {

    private int currentPanelIndex = 0;

    private static Vector<AddVehicleWindow> instances = new Vector<>();

    static void disableCloseButton() {
        for (AddVehicleWindow av : instances)
            av.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    static void enableCloseButton() {
        for (AddVehicleWindow av : instances)
            av.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    static void disposeAll() {
        for (AddVehicleWindow avw:instances)
            avw.dispose();
    }

    public String[] getJeepData() {
        return new String[]{jeepModelTextField.getText(), jeepMaxSpeedTextField.getText(), jeepMotorLifeTimeTextField.getText(), jeepFuelConsumptionTextField.getText()};
    }

    public String[] getFrigateData() {
        return new String[]{frigateModelTextField.getText(), frigateCapacityTextField.getText(), frigateMaxSpeedTextField.getText(), String.valueOf(frigateGoesWithTheWindCheckBox.isSelected())};
    }

    public String getSpyGliderData() {
        return spyGliderPowerSourceTextField.getText();
    }

    public String[] getAmphibianData() {
        return new String[]{amphibianModelTextField.getText(), amphibianCapacityTextField.getText(), amphibianMaxSpeedTextField.getText(), amphibianWheelsTextField.getText(), String.valueOf(amphibianGoesWithTheWindCheckBox.isSelected()), flagsTextsByIndexes[amphibianFlagComboBox.getSelectedIndex()], amphibianFuelConsumptionTextField.getText(), amphibianMotorLifeTimeTextField.getText()};
    }

    public String[] getBicycleData() {
        return new String[]{bicycleModelTextField.getText(), bicycleCapacityTextField.getText(), bicycleMaxSpeedTextField.getText(), (String) bicycleTerrainTypeComboBox.getSelectedItem()};
    }

    public String[] getElectricBicycleData() {
        return new String[]{electricBicycleModelTextField.getText(), electricBicycleCapacityTextField.getText(), electricBicycleMaxSpeedTextField.getText(), electricBicycleMotorLifeTimeTextField.getText(), (String) electricBicycleTerrainTypeComboBox.getSelectedItem()};
    }

    public String[] getCruiseData() {
        return new String[]{cruiseModelTextField.getText(), cruiseCapacityTextField.getText(), cruiseMaxSpeedTextField.getText(), cruiseFuelConsumptionTextField.getText(), cruiseMotorLifeTimeTextField.getText(), flagsTextsByIndexes[cruiseFlagComboBox.getSelectedIndex()]};
    }

    public String[] getHybridAirplaneData() {
        return new String[]{hybridPlaneModelTextField.getText(), hybridPlaneCapacityTextField.getText(), hybridPlaneMaxSpeedTextField.getText(), hybridPlaneFuelConsumptionTextField.getText(), hybridPlaneMotorLifeTimeTextField.getText(), String.valueOf(hybridPlaneGoesWithTheWindCheckBox.isSelected()), flagsTextsByIndexes[hybridPlaneFlagComboBox.getSelectedIndex()], hybridPlaneWheelsTextField.getText()};
    }

    public Icon getPicture() {
        if (pictureTypeComboBox.getSelectedIndex() == 0) // user chose a picture from the list
            return (Icon) pictureListComboBox.getSelectedItem();
        else // user uploaded a picture
            return uploadedPicture;
    }

    public String getColor() {
        return (String) colorsComboBox.getSelectedItem();
    }


    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_center_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private JTextField jeepModelTextField = new JTextField("model");
    private JTextField frigateModelTextField = new JTextField("model");
    private JTextField amphibianModelTextField = new JTextField("model");
    private JTextField bicycleModelTextField = new JTextField("model");
    private JTextField cruiseModelTextField = new JTextField("model");
    private JTextField electricBicycleModelTextField = new JTextField("model");
    private JTextField hybridPlaneModelTextField = new JTextField("model");

    private JTextField jeepMaxSpeedTextField = new JTextField("0");
    private JTextField frigateMaxSpeedTextField = new JTextField("0");
    private JTextField amphibianMaxSpeedTextField = new JTextField("0");
    private JTextField bicycleMaxSpeedTextField = new JTextField("0");
    private JTextField cruiseMaxSpeedTextField = new JTextField("0");
    private JTextField electricBicycleMaxSpeedTextField = new JTextField("0");
    private JTextField hybridPlaneMaxSpeedTextField = new JTextField("0");

    private JTextField jeepMotorLifeTimeTextField = new JTextField("0");
    private JTextField amphibianMotorLifeTimeTextField = new JTextField("0");
    private JTextField cruiseMotorLifeTimeTextField = new JTextField("0");
    private JTextField hybridPlaneMotorLifeTimeTextField = new JTextField("0");
    private JTextField electricBicycleMotorLifeTimeTextField = new JTextField("0");

    private JTextField jeepFuelConsumptionTextField = new JTextField("0");
    private JTextField amphibianFuelConsumptionTextField = new JTextField("0");
    private JTextField cruiseFuelConsumptionTextField = new JTextField("0");
    private JTextField hybridPlaneFuelConsumptionTextField = new JTextField("0");

    private JTextField frigateCapacityTextField = new JTextField("0");
    private JTextField amphibianCapacityTextField = new JTextField("0");
    private JTextField bicycleCapacityTextField = new JTextField("0");
    private JTextField cruiseCapacityTextField = new JTextField("0");
    private JTextField electricBicycleCapacityTextField = new JTextField("0");
    private JTextField hybridPlaneCapacityTextField = new JTextField("0");

    private JCheckBox frigateGoesWithTheWindCheckBox = new JCheckBox();
    private JCheckBox amphibianGoesWithTheWindCheckBox = new JCheckBox();
    private JCheckBox hybridPlaneGoesWithTheWindCheckBox = new JCheckBox();

    private JTextField spyGliderPowerSourceTextField = new JTextField("power source");

    private JTextField amphibianWheelsTextField = new JTextField("4");
    private JTextField hybridPlaneWheelsTextField = new JTextField("4");

    private Icon[] flags = {
            new ImageIcon(getClass().getResource("pics/israel.png")),
            new ImageIcon(getClass().getResource("pics/us.png")),
            new ImageIcon(getClass().getResource("pics/germany.png")),
            new ImageIcon(getClass().getResource("pics/italy.png")),
            new ImageIcon(getClass().getResource("pics/greece.png")),
            new ImageIcon(getClass().getResource("pics/somalia.png")),
            new ImageIcon(getClass().getResource("pics/pirate.png"))
    };
    private JComboBox<Icon> amphibianFlagComboBox = new JComboBox<>(flags);
    private JComboBox<Icon> cruiseFlagComboBox = new JComboBox<>(flags);
    private JComboBox<Icon> hybridPlaneFlagComboBox = new JComboBox<>(flags);
    private String[] flagsTextsByIndexes = {"Israel", "US", "Germany", "Italy", "Greece", "Somalia", "Pirate"};

    private String[] terrainTypes = {"Paved", "Dirt"};
    private JComboBox<String> bicycleTerrainTypeComboBox = new JComboBox<>(terrainTypes);
    private JComboBox<String> electricBicycleTerrainTypeComboBox = new JComboBox<>(terrainTypes);

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_bottom_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private Icon[] pictures = {
            new ImageIcon(getClass().getResource("pics/jeep.png")),
            new ImageIcon(getClass().getResource("pics/frigate.png")),
            new ImageIcon(getClass().getResource("pics/spyGlider.png")),
            new ImageIcon(getClass().getResource("pics/toyGlider.png")),
            new ImageIcon(getClass().getResource("pics/amphibian.png")),
            new ImageIcon(getClass().getResource("pics/bicycle.png")),
            new ImageIcon(getClass().getResource("pics/cruise.png")),
            new ImageIcon(getClass().getResource("pics/hybridPlane.png")),
            new ImageIcon(getClass().getResource("pics/electricBicycle.png")),
            new ImageIcon(getClass().getResource("pics/jeep1.png")),
            new ImageIcon(getClass().getResource("pics/jeep2.png")),
            new ImageIcon(getClass().getResource("pics/jeep3.png")),
            new ImageIcon(getClass().getResource("pics/frigate1.png")),
            new ImageIcon(getClass().getResource("pics/frigate2.png")),
            new ImageIcon(getClass().getResource("pics/frigate3.png")),
            new ImageIcon(getClass().getResource("pics/spyGlider1.png")),
            new ImageIcon(getClass().getResource("pics/spyGlider2.png")),
            new ImageIcon(getClass().getResource("pics/spyGlider3.png")),
            new ImageIcon(getClass().getResource("pics/toyGlider1.png")),
            new ImageIcon(getClass().getResource("pics/toyGlider2.png")),
            new ImageIcon(getClass().getResource("pics/toyGlider3.png")),
            new ImageIcon(getClass().getResource("pics/amphibian1.png")),
            new ImageIcon(getClass().getResource("pics/amphibian2.png")),
            new ImageIcon(getClass().getResource("pics/amphibian3.png")),
            new ImageIcon(getClass().getResource("pics/bicycle1.png")),
            new ImageIcon(getClass().getResource("pics/bicycle2.png")),
            new ImageIcon(getClass().getResource("pics/bicycle3.png")),
            new ImageIcon(getClass().getResource("pics/cruise1.png")),
            new ImageIcon(getClass().getResource("pics/cruise2.png")),
            new ImageIcon(getClass().getResource("pics/cruise3.png")),
            new ImageIcon(getClass().getResource("pics/hybridPlane1.png")),
            new ImageIcon(getClass().getResource("pics/hybridPlane2.png")),
            new ImageIcon(getClass().getResource("pics/hybridPlane3.png")),
            new ImageIcon(getClass().getResource("pics/electricBicycle1.png")),
            new ImageIcon(getClass().getResource("pics/electricBicycle2.png")),
            new ImageIcon(getClass().getResource("pics/electricBicycle3.png"))

    };
    private String[] pictureTypes = {"Choose from the list >>>", "Upload a picture"};
    private JComboBox<String> pictureTypeComboBox = new JComboBox<>(pictureTypes);
    private JFileChooser fileChooser = new JFileChooser();
    private JComboBox<Icon> pictureListComboBox = new JComboBox<>(pictures);
    private JButton uploadButton = new JButton("Upload a picture");
    private File uploadedPictureFile = null;
    private JLabel uploadedPictureLabel = new JLabel();
    private Icon uploadedPicture;
    private JComboBox<String> colorsComboBox = new JComboBox<>(new String[]{"Black", "Blue", "Red", "Green", "Purple","Yellow","Gray"});

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_right_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private JButton addButton = new JButton("Add");

    private IVehicle tmpVehicle = null;

    private void addVehicle(IVehicle v, boolean seaFlag,String color) {

        synchronized (UpdatingWindow.lock) { // only one procedure can update the system at the same time
            new UpdatingWindow();
            if (seaFlag)
                SeaVehicle.addSeaVehicle((ISeaVehicle) v);
            v = new StatusDecorator(new ColorDecorator(v,color),"Available");
            Vehicle.addVehicle(v);
            AdditionalOptionsWindow.callbackVehicleAdded(v);
            StockWindow.callbackVehicleAdded(v);
        }
        JOptionPane.showMessageDialog(addButton, "A vehicle was successfully added");
    }


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

    private void addTwoEmptyRows(JPanel gridPanelWithTwoColumns, String title) {
        // adds two empty rows and a title to a
        // panel with GridLayout
        JLabel tmp = new JLabel(title);
        tmp.setFont(new Font(null, Font.BOLD + Font.ITALIC, 16));
        gridPanelWithTwoColumns.add(tmp);
        for (int i = 0; i < 3; i++)
            gridPanelWithTwoColumns.add(new JLabel());
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_addVehicleWindow_constructor_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    AddVehicleWindow() {
        super("Add a Vehicle");
        setBounds(500, 200, 700, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_top_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel topPanel = new JPanel();
        FlowLayout topLayout = new FlowLayout();
        topPanel.setLayout(topLayout);
        topLayout.setAlignment(FlowLayout.LEFT);
        add(topPanel, BorderLayout.PAGE_START);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_center_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel mainPanel = new JPanel();
        JPanel amphibianPanel = new JPanel();
        JPanel amphibianCenterPanel = new JPanel();
        JPanel amphibianBottomPanel = new JPanel();
        JPanel cruisePanel = new JPanel();
        JPanel cruiseCenterPanel = new JPanel();
        JPanel cruiseBottomPanel = new JPanel();
        JPanel jeepPanel = new JPanel();
        JPanel frigatePanel = new JPanel();
        JPanel spyGliderPanel = new JPanel();
        JPanel toyGliderPanel = new JPanel();
        JPanel bicyclePanel = new JPanel();
        JPanel hybridPlanePanel = new JPanel();
        JPanel hybridPlaneCenterPanel = new JPanel();
        JPanel hybridPlaneBottomPanel = new JPanel();
        JPanel electricBicyclePanel = new JPanel();


        JLabel jeepModelLabel = new JLabel("               	Model: ");
        JLabel frigateModelLabel = new JLabel("               	Model: ");
        JLabel amphibianModelLabel = new JLabel("               	Model: ");
        JLabel bicycleModelLabel = new JLabel("               	Model: ");
        JLabel cruiseModelLabel = new JLabel("               	Model: ");
        JLabel hybridPlaneModelLabel = new JLabel("               	Model: ");
        JLabel electricBicycleModelLabel = new JLabel("               	Model: ");

        JLabel jeepMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel frigateMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel amphibianMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel bicycleMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel cruiseMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel hybridPlaneMaxSpeedLabel = new JLabel("               Max Speed: ");
        JLabel electricBicycleMaxSpeedLabel = new JLabel("               Max Speed: ");

        JLabel jeepMotorLifeTimeLabel = new JLabel("               Average motor lifetime: ");
        JLabel amphibianMotorLifeTimeLabel = new JLabel("               Average motor lifetime: ");
        JLabel cruiseMotorLifeTimeLabel = new JLabel("               Average motor lifetime: ");
        JLabel hybridPlaneMotorLifeTimeLabel = new JLabel("               Average motor lifetime: ");
        JLabel electricBicycleMotorLifeTimeLabel = new JLabel("               Average motor lifetime: ");

        JLabel jeepFuelConsumptionLabel = new JLabel("               Average fuel consumption: ");
        JLabel amphibianFuelConsumptionLabel = new JLabel("               Average fuel consumption: ");
        JLabel cruiseFuelConsumptionLabel = new JLabel("               Average fuel consumption: ");
        JLabel hybridPlaneFuelConsumptionLabel = new JLabel("               Average fuel consumption: ");

        JLabel frigateCapacityLabel = new JLabel("               Capacity: ");
        JLabel amphibianCapacityLabel = new JLabel("               Capacity: ");
        JLabel bicycleCapacityLabel = new JLabel("               Capacity: ");
        JLabel cruiseCapacityLabel = new JLabel("               Capacity: ");
        JLabel hybridPlaneCapacityLabel = new JLabel("               Capacity: ");
        JLabel electricBicycleCapacityLabel = new JLabel("               Capacity: ");

        JLabel frigateGoesWithTheWindLabel = new JLabel("               Goes with the wind: ");
        JLabel amphibianGoesWithTheWindLabel = new JLabel("               Goes with the wind: ");
        JLabel hybridPlaneGoesWithTheWindLabel = new JLabel("               Goes with the wind: ");

        JLabel spyGliderPowerSourceLabel = new JLabel("               Power Source: ");

        JLabel amphibianWheelsLabel = new JLabel("               Number of wheels: ");
        JLabel hybridPlaneWheelsLabel = new JLabel("               Number of wheels: ");

        JLabel amphibianFlagLabel = new JLabel("               Flag: ");
        JLabel cruiseFlagLabel = new JLabel("               Flag: ");
        JLabel hybridPlaneFlagLabel = new JLabel("               Flag: ");

        JLabel bicycleTerrainTypeLabel = new JLabel("               Terrain type: ");
        JLabel electricBicycleTerrainTypeLabel = new JLabel("               Terrain type: ");

        // ---------------_jeep_panel_---------------
        jeepPanel.setLayout(new GridLayout(6, 2));
        addTwoEmptyRows(jeepPanel, "Jeep:");
        jeepPanel.add(jeepModelLabel);
        jeepPanel.add(jeepModelTextField);
        jeepPanel.add(jeepMaxSpeedLabel);
        jeepPanel.add(jeepMaxSpeedTextField);
        jeepPanel.add(jeepMotorLifeTimeLabel);
        jeepPanel.add(jeepMotorLifeTimeTextField);
        jeepPanel.add(jeepFuelConsumptionLabel);
        jeepPanel.add(jeepFuelConsumptionTextField);
        mainPanel.add(jeepPanel); // default panel is jeep so no setVisible(false)
        // ---------------_jeep_panel_end_---------------

        // ---------------_frigate_panel_---------------
        frigatePanel.setLayout(new GridLayout(6, 2));
        addTwoEmptyRows(frigatePanel, "Frigate:");
        frigatePanel.setVisible(false);
        frigatePanel.add(frigateModelLabel);
        frigatePanel.add(frigateModelTextField);
        frigatePanel.add(frigateCapacityLabel);
        frigatePanel.add(frigateCapacityTextField);
        frigatePanel.add(frigateMaxSpeedLabel);
        frigatePanel.add(frigateMaxSpeedTextField);
        frigatePanel.add(frigateGoesWithTheWindLabel);
        frigatePanel.add(frigateGoesWithTheWindCheckBox);
        mainPanel.add(frigatePanel);
        // ---------------_frigate_panel_end_---------------

        // ---------------_spy_glider_panel_---------------
        spyGliderPanel.setLayout(new GridLayout(3, 2));
        addTwoEmptyRows(spyGliderPanel, "Spy Glider:");
        spyGliderPanel.setVisible(false);
        spyGliderPanel.add(spyGliderPowerSourceLabel);
        spyGliderPanel.add(spyGliderPowerSourceTextField);
        mainPanel.add(spyGliderPanel);

        // ---------------_spy_glider_panel_end_---------------

        // ---------------_toy_glider_panel_---------------
        toyGliderPanel.setLayout(new GridLayout(2, 2));
        addTwoEmptyRows(toyGliderPanel, "Toy Glider:");
        toyGliderPanel.setVisible(false);
        mainPanel.add(toyGliderPanel);
        // ---------------_toy_glider_panel_end_---------------

        // ---------------_amphibian_panel_---------------
        amphibianPanel.setLayout(new BorderLayout());
        amphibianPanel.setVisible(false);
        amphibianCenterPanel.setLayout(new GridLayout(9, 2));
        addTwoEmptyRows(amphibianCenterPanel, "Amphibian:");
        amphibianCenterPanel.add(amphibianModelLabel);
        amphibianCenterPanel.add(amphibianModelTextField);
        amphibianCenterPanel.add(amphibianMaxSpeedLabel);
        amphibianCenterPanel.add(amphibianMaxSpeedTextField);
        amphibianCenterPanel.add(amphibianMotorLifeTimeLabel);
        amphibianCenterPanel.add(amphibianMotorLifeTimeTextField);
        amphibianCenterPanel.add(amphibianFuelConsumptionLabel);
        amphibianCenterPanel.add(amphibianFuelConsumptionTextField);
        amphibianCenterPanel.add(amphibianCapacityLabel);
        amphibianCenterPanel.add(amphibianCapacityTextField);
        amphibianCenterPanel.add(amphibianGoesWithTheWindLabel);
        amphibianCenterPanel.add(amphibianGoesWithTheWindCheckBox);
        amphibianCenterPanel.add(amphibianWheelsLabel);
        amphibianCenterPanel.add(amphibianWheelsTextField);
        amphibianPanel.add(amphibianCenterPanel, BorderLayout.CENTER);
        amphibianBottomPanel.setLayout(new GridLayout(1, 2));
        amphibianBottomPanel.add(amphibianFlagLabel);
        amphibianBottomPanel.add(amphibianFlagComboBox);
        amphibianPanel.add(amphibianBottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(amphibianPanel);
        // ---------------_amphibian_panel_end---------------

        // ---------------_bicycle_panel_---------------
        bicyclePanel.setLayout(new GridLayout(6, 2));
        bicyclePanel.setVisible(false);
        addTwoEmptyRows(bicyclePanel, "Bicycle: ");
        bicyclePanel.add(bicycleModelLabel);
        bicyclePanel.add(bicycleModelTextField);
        bicyclePanel.add(bicycleMaxSpeedLabel);
        bicyclePanel.add(bicycleMaxSpeedTextField);
        bicyclePanel.add(bicycleCapacityLabel);
        bicyclePanel.add(bicycleCapacityTextField);
        bicyclePanel.add(bicycleTerrainTypeLabel);
        bicyclePanel.add(bicycleTerrainTypeComboBox);
        mainPanel.add(bicyclePanel);
        // ---------------_bicycle_panel_end_---------------

        // ---------------_cruise_panel_---------------
        cruisePanel.setLayout(new BorderLayout());
        cruisePanel.setVisible(false);
        cruiseCenterPanel.setLayout(new GridLayout(7, 2));
        addTwoEmptyRows(cruiseCenterPanel, "Cruise:");
        cruiseCenterPanel.add(cruiseModelLabel);
        cruiseCenterPanel.add(cruiseModelTextField);
        cruiseCenterPanel.add(cruiseCapacityLabel);
        cruiseCenterPanel.add(cruiseCapacityTextField);
        cruiseCenterPanel.add(cruiseMaxSpeedLabel);
        cruiseCenterPanel.add(cruiseMaxSpeedTextField);
        cruiseCenterPanel.add(cruiseMotorLifeTimeLabel);
        cruiseCenterPanel.add(cruiseMotorLifeTimeTextField);
        cruiseCenterPanel.add(cruiseFuelConsumptionLabel);
        cruiseCenterPanel.add(cruiseFuelConsumptionTextField);
        cruisePanel.add(cruiseCenterPanel, BorderLayout.CENTER);
        cruiseBottomPanel.setLayout(new GridLayout(1, 2));
        cruiseBottomPanel.add(cruiseFlagLabel);
        cruiseBottomPanel.add(cruiseFlagComboBox);
        cruisePanel.add(cruiseBottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(cruisePanel);
        // ---------------_cruise_panel_end_---------------

        // ---------------_hybrid_plane_panel_---------------
        hybridPlanePanel.setLayout(new BorderLayout());
        hybridPlanePanel.setVisible(false);
        hybridPlaneCenterPanel.setLayout(new GridLayout(9, 2));
        addTwoEmptyRows(hybridPlaneCenterPanel, "Hybrid Plane:");
        hybridPlaneCenterPanel.add(hybridPlaneModelLabel);
        hybridPlaneCenterPanel.add(hybridPlaneModelTextField);
        hybridPlaneCenterPanel.add(hybridPlaneCapacityLabel);
        hybridPlaneCenterPanel.add(hybridPlaneCapacityTextField);
        hybridPlaneCenterPanel.add(hybridPlaneMaxSpeedLabel);
        hybridPlaneCenterPanel.add(hybridPlaneMaxSpeedTextField);
        hybridPlaneCenterPanel.add(hybridPlaneFuelConsumptionLabel);
        hybridPlaneCenterPanel.add(hybridPlaneFuelConsumptionTextField);
        hybridPlaneCenterPanel.add(hybridPlaneMotorLifeTimeLabel);
        hybridPlaneCenterPanel.add(hybridPlaneMotorLifeTimeTextField);
        hybridPlaneCenterPanel.add(hybridPlaneGoesWithTheWindLabel);
        hybridPlaneCenterPanel.add(hybridPlaneGoesWithTheWindCheckBox);
        hybridPlaneCenterPanel.add(hybridPlaneWheelsLabel);
        hybridPlaneCenterPanel.add(hybridPlaneWheelsTextField);
        hybridPlanePanel.add(hybridPlaneCenterPanel, BorderLayout.CENTER);
        hybridPlaneBottomPanel.setLayout(new GridLayout(1, 2));
        hybridPlaneBottomPanel.add(hybridPlaneFlagLabel);
        hybridPlaneBottomPanel.add(hybridPlaneFlagComboBox);
        hybridPlanePanel.add(hybridPlaneBottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(hybridPlanePanel);
        // ---------------_hybrid_plane_panel_end_---------------

        // ---------------_electric_bicycle_panel_---------------
        electricBicyclePanel.setLayout(new GridLayout(7, 2));
        electricBicyclePanel.setVisible(false);
        addTwoEmptyRows(electricBicyclePanel, "Electric Bicycle:");
        electricBicyclePanel.add(electricBicycleModelLabel);
        electricBicyclePanel.add(electricBicycleModelTextField);
        electricBicyclePanel.add(electricBicycleCapacityLabel);
        electricBicyclePanel.add(electricBicycleCapacityTextField);
        electricBicyclePanel.add(electricBicycleMaxSpeedLabel);
        electricBicyclePanel.add(electricBicycleMaxSpeedTextField);
        electricBicyclePanel.add(electricBicycleMotorLifeTimeLabel);
        electricBicyclePanel.add(electricBicycleMotorLifeTimeTextField);
        electricBicyclePanel.add(electricBicycleTerrainTypeLabel);
        electricBicyclePanel.add(electricBicycleTerrainTypeComboBox);
        mainPanel.add(electricBicyclePanel);
        // ---------------_electric_bicycle_panel_end_---------------


        add(mainPanel, BorderLayout.CENTER);
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_bottom_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel bottomPanel = new JPanel();
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new GridLayout(2, 2, 2, 2));
        bottomPanel.setLayout(new FlowLayout());
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Choose a picture to upload");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF", "gif"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        uploadedPictureLabel.setVisible(false);
        uploadButton.setVisible(false);
        pictureTypeComboBox.addItemListener(l -> { // switching picture type ( choose / upload )
                    if (pictureTypeComboBox.getSelectedIndex() == 0) { // switched to "Choose from the list"
                        uploadedPictureLabel.setVisible(false);
                        uploadButton.setVisible(false);
                        pictureListComboBox.setVisible(true);
                    } else // switched to "Upload a picture"
                    {
                        uploadButton.setVisible(true);
                        pictureListComboBox.setVisible(false);
                    }
                }
        );
        uploadButton.addActionListener(l -> {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                uploadedPictureFile = fileChooser.getSelectedFile();
                if (uploadedPictureFile != null) // user actually chose a file
                {
                    uploadedPicture = new ImageIcon(new ImageIcon(uploadedPictureFile.getAbsolutePath()).getImage()
                            .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    uploadedPictureLabel.setIcon(uploadedPicture);
                    uploadButton.setVisible(false);
                    uploadedPictureLabel.setVisible(true);
                }
            }
        });
        bottomLeftPanel.add(new JLabel("Color:"));
        bottomLeftPanel.add(new JLabel("Picture: "));
        bottomLeftPanel.add(colorsComboBox);
        bottomLeftPanel.add(pictureTypeComboBox);
        bottomPanel.add(bottomLeftPanel);
        bottomPanel.add(uploadedPictureLabel);
        bottomPanel.add(pictureListComboBox);
        bottomPanel.add(uploadButton);
        add(bottomPanel, BorderLayout.PAGE_END);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_left_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1, 10, 10));
        JLabel addLabel = new JLabel("Add a:");
        JButton jeepButton = new JButton("Jeep (Default)");
        JButton frigateButton = new JButton("Frigate");
        JButton spyGliderButton = new JButton("Spy Glider");
        JButton toyGliderButton = new JButton("Toy Glider");
        JButton amphibianButton = new JButton("Amphibian");
        JButton bicycleButton = new JButton("Bicycle");
        JButton cruiseButton = new JButton("Cruise");
        JButton hybridPlaneButton = new JButton("Hybrid Plane");
        JButton electricBicycleButton = new JButton("Electric Bicycle");

        // ---------------_buttons listeners_---------------

        jeepButton.addActionListener(l -> {
            if (currentPanelIndex != 0) {
                switch (currentPanelIndex) {
                    case 1: // switch from frigate to jeep
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to jeep
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to jeep
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to jeep
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to jeep
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to jeep
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to jeep
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to jeep
                        electricBicyclePanel.setVisible(false);

                }
                jeepPanel.setVisible(true);
                currentPanelIndex = 0;
                pictureListComboBox.setSelectedIndex(0);
                colorsComboBox.setSelectedIndex(0);
            }
        });

        frigateButton.addActionListener(l -> {
            if (currentPanelIndex != 1) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to frigate
                        jeepPanel.setVisible(false);
                    case 2: // switch from spy glider to frigate
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to frigate
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to frigate
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to frigate
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to frigate
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to frigate
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to frigate
                        electricBicyclePanel.setVisible(false);
                }
                frigatePanel.setVisible(true);
                currentPanelIndex = 1;
                pictureListComboBox.setSelectedIndex(1);
                colorsComboBox.setSelectedIndex(6);
            }
        });

        spyGliderButton.addActionListener(l -> {
            if (currentPanelIndex != 2) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to spy glider
                        jeepPanel.setVisible(false);
                    case 1: // switch from spy glider to spy glider
                        frigatePanel.setVisible(false);
                    case 3: // switch from toy glider to spy glider
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to spy glider
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to spy glider
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to spy glider
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to spy glider
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to spy glider
                        electricBicyclePanel.setVisible(false);
                }
                spyGliderPanel.setVisible(true);
                currentPanelIndex = 2;
                pictureListComboBox.setSelectedIndex(2);
                colorsComboBox.setSelectedIndex(0);
            }
        });

        toyGliderButton.addActionListener(l -> {
            if (currentPanelIndex != 3) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to toy glider
                        jeepPanel.setVisible(false);
                    case 1: // switch from spy glider to toy glider
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to toy glider
                        spyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to toy glider
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to toy glider
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to toy glider
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to toy glider
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to toy glider
                        electricBicyclePanel.setVisible(false);
                }
                toyGliderPanel.setVisible(true);
                currentPanelIndex = 3;
                pictureListComboBox.setSelectedIndex(3);
                colorsComboBox.setSelectedIndex(2);
            }
        });

        amphibianButton.addActionListener(l -> {
            if (currentPanelIndex != 4) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to amphibian
                        jeepPanel.setVisible(false);
                    case 1: // switch from frigate to amphibian
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to amphibian
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to amphibian
                        toyGliderPanel.setVisible(false);
                    case 5: // switch from bicycle to amphibian
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to amphibian
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to amphibian
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to amphibian
                        electricBicyclePanel.setVisible(false);
                }
                amphibianPanel.setVisible(true);
                currentPanelIndex = 4;
                pictureListComboBox.setSelectedIndex(4);
                colorsComboBox.setSelectedIndex(5);
            }
        });

        bicycleButton.addActionListener(l -> {
            if (currentPanelIndex != 5) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to bicycle
                        jeepPanel.setVisible(false);
                    case 1: // switch from frigate to bicycle
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to bicycle
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to bicycle
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to bicycle
                        amphibianPanel.setVisible(false);
                    case 6: // switch from cruise to bicycle
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to bicycle
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to bicycle
                        electricBicyclePanel.setVisible(false);
                }
                bicyclePanel.setVisible(true);
                currentPanelIndex = 5;
                pictureListComboBox.setSelectedIndex(5);
                colorsComboBox.setSelectedIndex(0);
            }
        });

        cruiseButton.addActionListener(l -> {
            if (currentPanelIndex != 6) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to cruise
                        jeepPanel.setVisible(false);
                    case 1: // switch from frigate to cruise
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to cruise
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to cruise
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to cruise
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to cruise
                        bicyclePanel.setVisible(false);
                    case 7: // switch from hybrid plane to cruise
                        hybridPlanePanel.setVisible(false);
                    case 8: // switch from electric bicycle to cruise
                        electricBicyclePanel.setVisible(false);
                }
                cruisePanel.setVisible(true);
                currentPanelIndex = 6;
                pictureListComboBox.setSelectedIndex(6);
                colorsComboBox.setSelectedIndex(1);
            }
        });

        hybridPlaneButton.addActionListener(l -> {
            if (currentPanelIndex != 7) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to hybrid plane
                        jeepPanel.setVisible(false);
                    case 1: // switch from frigate to hybrid plane
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to hybrid plane
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to hybrid plane
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to hybrid plane
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to hybrid plane
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to hybrid plane
                        cruisePanel.setVisible(false);
                    case 8: // switch from electric bicycle to hybrid plane
                        electricBicyclePanel.setVisible(false);
                }
                hybridPlanePanel.setVisible(true);
                currentPanelIndex = 7;
                pictureListComboBox.setSelectedIndex(7);
                colorsComboBox.setSelectedIndex(6);
            }
        });

        electricBicycleButton.addActionListener(l -> {
            if (currentPanelIndex != 8) {
                switch (currentPanelIndex) {
                    case 0: // switch from jeep to electric bicycle
                        jeepPanel.setVisible(false);
                    case 1: // switch from frigate to electric bicycle
                        frigatePanel.setVisible(false);
                    case 2: // switch from spy glider to electric bicycle
                        spyGliderPanel.setVisible(false);
                    case 3: // switch from toy glider to electric bicycle
                        toyGliderPanel.setVisible(false);
                    case 4: // switch from amphibian to electric bicycle
                        amphibianPanel.setVisible(false);
                    case 5: // switch from bicycle to electric bicycle
                        bicyclePanel.setVisible(false);
                    case 6: // switch from cruise to electric bicycle
                        cruisePanel.setVisible(false);
                    case 7: // switch from hybrid plane to electric bicycle
                        hybridPlanePanel.setVisible(false);

                }
                electricBicyclePanel.setVisible(true);
                currentPanelIndex = 8;
                pictureListComboBox.setSelectedIndex(8);
                colorsComboBox.setSelectedIndex(5);
            }
        });

        // ---------------_buttons listeners end_---------------

        leftPanel.add(addLabel);
        leftPanel.add(jeepButton);
        leftPanel.add(frigateButton);
        leftPanel.add(spyGliderButton);
        leftPanel.add(toyGliderButton);
        leftPanel.add(amphibianButton);
        leftPanel.add(bicycleButton);
        leftPanel.add(cruiseButton);
        leftPanel.add(hybridPlaneButton);
        leftPanel.add(electricBicycleButton);
        add(leftPanel, BorderLayout.LINE_START);

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<_right_>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1, 1));

        addButton.addActionListener(l -> {

            switch (currentPanelIndex) {
                case 0: // jeep
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Ground").makeVehicle("Jeep", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, false,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 1: // frigate
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Sea").makeVehicle("Frigate", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, true,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 2: // spyGlider
                    tmpVehicle = GeneralFactory.getFactory("Air").makeVehicle("Spy Glider", this);
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            addVehicle(tmpVehicle, false,getColor());
                            return null;
                        }
                    }.execute();
                    break;
                case 3: // toyGlider
                    tmpVehicle = GeneralFactory.getFactory("Air").makeVehicle("Toy Glider", this);
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            addVehicle(tmpVehicle, false,getColor());
                            return null;
                        }
                    }.execute();
                    break;
                case 4: // amphibian
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Sea").makeVehicle("Amphibian", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, true,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 5: // bicycle
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Ground").makeVehicle("Bicycle", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, false,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 6: // cruise
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Sea").makeVehicle("Cruise", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, true,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 7: // hybrid airplane
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Sea").makeVehicle("Hybrid Airplane", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, true,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
                case 8: // electric bicycle
                    try {
                        tmpVehicle = GeneralFactory.getFactory("Ground").makeVehicle("Electric Bicycle", this);
                        new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() {
                                addVehicle(tmpVehicle, false,getColor());
                                return null;
                            }
                        }.execute();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(addButton, "Wrong input,try again");
                    }
                    break;
            }
        });
        rightPanel.add(addButton);
        add(rightPanel, BorderLayout.LINE_END);
        setVisible(true);
        instances.add(this);
    }
}