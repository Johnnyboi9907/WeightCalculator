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
    private ListView<String> listView;

    @FXML
    private Label nameLbl;

    @FXML
    private Button selectBtn;

    private String[] planets;

    @FXML
    public void initialize() {
        Planet earth = new Planet("Earth", 9.8, new Image("images/Earth.jpg"));
        Planet moon = new Planet("Moon", 1.6, new Image("images/Moon.jpg"));
        Planet mars = new Planet("Mars", 3.7, new Image("images/Mars.png"));
        Planet venus = new Planet("Venus", 8.87, new Image("images/Venus.jpg"));
        Planet jupiter = new Planet("Jupiter", 24.5, new Image("images/Jupiter.png"));
        Planet sun = new Planet("Sun", 275, new Image("images/Sun.jpg"));

        // create an array of Strings containing all the names of the planets
        planets = new String[]{"Earth", "Moon", "Mars", "Venus", "Jupiter", "Sun"};

        // Add all Planet objects to ListView
        listView.getItems().addAll(planets);

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
                        break;

                    case "Moon":
                        nameLbl.setText(moon.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(moon.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(moon.getImage());
                        break;

                    case "Mars":
                        nameLbl.setText(mars.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(mars.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(mars.getImage());
                        break;

                    case "Venus":
                        nameLbl.setText(venus.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(venus.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(venus.getImage());
                        break;

                    case "Jupiter":
                        nameLbl.setText(jupiter.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(jupiter.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(jupiter.getImage());
                        break;

                    case "Sun":
                        nameLbl.setText(sun.getName());
                        accelerationLbl.setText("Acceleration due to gravity = " + String.valueOf(sun.getAcceleration()) + " m/s\u00B2");
                        imgView.setImage(sun.getImage());
                        break;
                }
            }
        });

    }
    
    // method for button set on action -> should return back to the main screen and confirm the planet selection (maybe add another void method to return the selected acceleration)
}
