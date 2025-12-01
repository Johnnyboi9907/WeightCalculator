package weightcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller for the Object selection screen. Handles predefined objects and a
 * custom object where the user can set mass via slider or textfield.
 */
public class ObjectController {

    // UI elements injected from FXML
    @FXML
    private Button addBtn;
    @FXML
    private ImageView imgView;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label massLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private SplitPane splitpane;
    @FXML
    private Slider customMassSlider;
    @FXML
    private Label customMasslbl;
    @FXML
    private TextField customMasstf;

    // Data structures
    private String[] items;
    private Object[] objects;
    private Object selectedObject;
    private MainController mc;
    public boolean ready;

    public void setMainController(MainController main) {
        this.mc = main;
    }

    public Object getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Object selectedObject) {
        this.selectedObject = selectedObject;
    }

    @FXML
    public void initialize() {
        // Create predefined objects with name, mass (grams), and image
        Object apple = new Object("Apple", 100, new Image("images/apple.jpg"));
        Object books = new Object("Pile of books", 700, new Image("images/books.png"));
        Object gold = new Object("Gold bar", 1000, new Image("images/goldbar.png"));
        Object human = new Object("Human", 70000, new Image("images/person.png"));
        Object gorilla = new Object("Gorilla", 160000, new Image("images/gorilla.png"));
        Object car = new Object("Bugatti Chiron", 2000000, new Image("images/car.png"));

        addBtn.setDisable(true);

        // Hide custom controls initially
        customMassSlider.setVisible(false);
        customMasstf.setVisible(false);
        customMasslbl.setVisible(false);

        // Default slider value
        customMassSlider.setValue(1000);

        ready = false;

        // Populate listView with object names
        items = new String[]{"Apple", "Pile of books", "Gold bar", "Human", "Gorilla", "Bugatti Chiron", "Custom"};
        objects = new Object[]{apple, books, gold, human, gorilla, car};
        listView.getItems().addAll(items);

        // Listener: keep slider and textfield in sync
        customMassSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int customMass = newVal.intValue();
            customMasstf.setText(String.valueOf(customMass));
            customMasslbl.setText(customMass + " g");
            massLbl.setText("Mass = " + customMass + " g");
        });

        // Listener: validate textfield input and update slider/labels
        customMasstf.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                return;
            }
            try {
                int value = Integer.parseInt(newVal);

                if (value <= 0) {
                    showAlert(Alert.AlertType.WARNING, "Invalid Mass", "Mass must be greater than 0 g.");
                    value = 1;
                } else if (value > 1000000) {
                    showAlert(Alert.AlertType.WARNING, "Invalid Mass", "Mass cannot exceed 1,000,000 g.");
                    value = 1000000;
                }

                customMassSlider.setValue(value);
                massLbl.setText("Mass = " + value + " g");
                customMasslbl.setText(value + " g");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for mass.");
                customMasstf.setText(oldVal); // revert to last valid value
            }
        });

        // Listener: handle selection from listView
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                return;
            }

            switch (newVal) {
                case "Apple":
                    setObjectDisplay(apple);
                    break;
                case "Pile of books":
                    setObjectDisplay(books);
                    break;
                case "Gold bar":
                    setObjectDisplay(gold);
                    break;
                case "Human":
                    setObjectDisplay(human);
                    break;
                case "Gorilla":
                    setObjectDisplay(gorilla);
                    break;
                case "Bugatti Chiron":
                    setObjectDisplay(car);
                    break;
                case "Custom":
                    nameLbl.setText("Custom Object");
                    imgView.setImage(null); // no image for custom
                    customMassSlider.setValue(1000);
                    customMasstf.setText("1000");
                    massLbl.setText("Mass = 1000 g");
                    customMasslbl.setText("1000 g");
                    customMassSlider.setVisible(true);
                    customMasstf.setVisible(true);
                    customMasslbl.setVisible(true);
                    addBtn.setDisable(false);
                    break;
            }
        });
    }

    // Helper method to set display for predefined objects
    private void setObjectDisplay(Object obj) {
        nameLbl.setText(obj.getName());
        massLbl.setText("Mass = " + obj.getMass() + " g");
        imgView.setImage(obj.getImage());
        addBtn.setDisable(false);

        // Hide custom controls when a predefined object is selected
        customMassSlider.setVisible(false);
        customMasstf.setVisible(false);
        customMasslbl.setVisible(false);
    }

    // Handle Add button click
    @FXML
    void handleAdd(ActionEvent event) {
        String selected = listView.getSelectionModel().getSelectedItem();

        // Prevent adding more than 5 objects
        if (mc.getObjectCount() >= 5) {
            showAlert(Alert.AlertType.WARNING, "Limit Reached", "You can only add up to 5 objects.");
            return;
        }

        if (selected.equals("Custom")) {
            if (customMasstf.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Missing Mass", "Please enter a mass before adding the custom object.");
                return;
            }

            int customMass = (int) customMassSlider.getValue();

            // Load placeholder image for custom objects
            Image customImage;
            try {
                customImage = new Image(getClass().getResource("/images/custom.png").toExternalForm());
            } catch (NullPointerException e) {
                // fallback if image not found
                customImage = null;
            }

            Object customObject = new Object("Custom Object", customMass, customImage);
            this.setSelectedObject(customObject);

            // Pass name, image, and mass to MainController
            mc.updateObjectDisplay(customObject.getName(), customImage, customMass);
            ready = true;
        } else {
            for (int i = 0; i < items.length - 1; i++) { // exclude "Custom"
                if (items[i].equals(selected)) {
                    this.setSelectedObject(objects[i]);
                    Object obj = objects[i];
                    mc.updateObjectDisplay(obj.getName(), obj.getImage(), obj.getMass());
                    ready = true;
                }
            }
        }

        Stage window = (Stage) addBtn.getScene().getWindow();
        window.close();
    }

    // Reusable alert method
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

/*
 * List of objects:
 * mass of an apple = 100g
 * mass of a pile of books = 700g
 * mass of a gold bar = 1000g
 * mass of a human = 70 kg = 70000g
 * mass of a gorilla = 160kg = 160000g
 * mass of a bugatti chiron = 2000kg = 2000000g
 */
