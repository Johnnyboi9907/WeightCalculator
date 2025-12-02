package weightcalculator;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PlanetController {

    @FXML
    Label accelerationLbl;

    @FXML
    ImageView imgView;

    @FXML
    ListView<String> listView;

    @FXML
    Label nameLbl;

    @FXML
    Button selectBtn;

    String[] items;
    Planet[] planets;
    Planet selectedPlanet;
    MainController mc;
    boolean ready;
    Image semiImage;

    public Image getSemiImage() {
        return semiImage;
    }

    public void setMainController(MainController main) {
        this.mc = main;
    }

    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }

    public void setSelectedPlanet(Planet selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }

    @FXML
    public void initialize() {
        Planet earth = new Planet("Earth", 9.8,
                new Image(getClass().getResourceAsStream("/images/Earth.jpg")),
                new Image(getClass().getResourceAsStream("/images/EarthSemi.jpg")));

        Planet moon = new Planet("Moon", 1.6,
                new Image(getClass().getResourceAsStream("/images/Moon.jpg")),
                new Image(getClass().getResourceAsStream("/images/MoonSemi.jpg")));

        Planet mars = new Planet("Mars", 3.7,
                new Image(getClass().getResourceAsStream("/images/Mars.png")),
                new Image(getClass().getResourceAsStream("/images/MarsSemi.png")));

        Planet venus = new Planet("Venus", 8.87,
                new Image(getClass().getResourceAsStream("/images/Venus.jpg")),
                new Image(getClass().getResourceAsStream("/images/VenusSemi.jpg")));

        Planet jupiter = new Planet("Jupiter", 24.5,
                new Image(getClass().getResourceAsStream("/images/Jupiter.png")),
                new Image(getClass().getResourceAsStream("/images/JupiterSemi.png")));

        Planet sun = new Planet("Sun", 275,
                new Image(getClass().getResourceAsStream("/images/Sun.jpg")),
                new Image(getClass().getResourceAsStream("/images/SunSemi.jpg")));

        ready = false;

        items = new String[]{"Earth", "Moon", "Mars", "Venus", "Jupiter", "Sun"}; // initialize an array of Strings containing the names of the items on the listview
        planets = new Planet[]{earth, moon, mars, venus, jupiter, sun}; // an array containing all the planets

        listView.getItems().addAll(items); // add the array of item names into the list

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {

                if (newVal == null) {
                    return;
                }

                switch (newVal) {
                    case "Earth":
                        nameLbl.setText(earth.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(earth.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(earth.getImage());
                        selectBtn.setDisable(false);
                        break;

                    case "Moon":
                        nameLbl.setText(moon.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(moon.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(moon.getImage());
                        selectBtn.setDisable(false);
                        break;

                    case "Mars":
                        nameLbl.setText(mars.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(mars.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(mars.getImage());
                        selectBtn.setDisable(false);
                        break;

                    case "Venus":
                        nameLbl.setText(venus.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(venus.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(venus.getImage());
                        selectBtn.setDisable(false);
                        break;

                    case "Jupiter":
                        nameLbl.setText(jupiter.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(jupiter.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(jupiter.getImage());
                        selectBtn.setDisable(false);
                        break;

                    case "Sun":
                        nameLbl.setText(sun.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(sun.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(sun.getImage());
                        selectBtn.setDisable(false);
                        break;
                }
            }
        });

    }

    // find the object of the chosen list view item and then take its image and display it on the main screen (on the scale)
    @FXML
    void handleSelect(ActionEvent event) throws IOException {
        String selected = listView.getSelectionModel().getSelectedItem();

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(selected)) {
                this.setSelectedPlanet(planets[i]);
                ready = true;

                // Send semi image to main screen
                if (mc != null) {
                    mc.updateSelectedPlanet(planets[i].getName(), planets[i].getSemiImage());
                }
            }
        }

        Stage stage = (Stage) selectBtn.getScene().getWindow();
        stage.close();
    }

}
