package weightcalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlanetController {

    @FXML
    private Label accelerationLbl;

    @FXML
    private ImageView imgView;

    @FXML
    private ListView<Planet > listView;

    @FXML
    private Label nameLbl;

    @FXML
    private Button selectBtn;

    Planet currentPlanet;

    @FXML
    public void initialize() {
        Planet earth = new Planet("Earth", 9.8, new Image("images/Earth.jpg"));
        Planet moon = new Planet("Moon", 1.6, new Image("images/Moon.jpg"));
        Planet mars = new Planet("Mars", 3.7, new Image("images/Mars.png"));
        Planet venus = new Planet("Venus", 8.87, new Image("images/Venus.jpg"));
        Planet jupiter = new Planet("Jupiter", 24.5, new Image("images/Jupiter.png"));
        Planet sun = new Planet("Sun", 275, new Image("images/Sun.jpg"));

         // Add planets to ListView
        listView.getItems().addAll(earth, moon, mars, venus, jupiter, sun);

        // Display the Planet name instead of the object reference
        listView.setCellFactory(param -> new javafx.scene.control.ListCell<Planet>() {
            @Override
            protected void updateItem(Planet planet, boolean empty) {
                super.updateItem(planet, empty);
                setText(empty || planet == null ? null : planet.getName());
            }
        });

        // Listener to update UI when a planet is selected
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Planet>() {
            @Override
            public void changed(ObservableValue<? extends Planet> obs, Planet oldVal, Planet newVal) {
                if (newVal != null) {
                    currentPlanet = newVal;

                    nameLbl.setText(newVal.getName());
                    accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(newVal.getAcceleration()) + " m/s2");
                    imgView.setImage(newVal.getImage());
                }
            }
        });
    }
}
