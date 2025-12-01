package weightcalculator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * Handles menus, opening object/planet selection screens, displaying selected
 * object/planet, and calculating weight.
 */
public class MainController {

    @FXML
    private MenuItem clearBtn;
    @FXML
    private MenuItem closeBtn;
    @FXML
    private MenuItem objectBtn;
    @FXML
    private MenuItem planetBtn;
    @FXML
    private MenuItem resetBtn;
    @FXML
    private MenuItem runBtn;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private MenuItem undoBtn;
    @FXML
    private Label weightLbl;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ImageView imgView;              // image of selected object
    @FXML
    private Label errorlbl;                 // error messages
    @FXML
    private Label selectedPlanetlbl;        // name of selected planet
    @FXML
    private PlanetController planetPaneController;
    @FXML
    private ImageView planetSelectediv;     // image of selected planet

    // NEW: label to show selected object name (add it in your main FXML)
    @FXML
    private Label selectedObjectlbl;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Physics values
    private double weight;
    private double mass;          // in kg (object mass in grams / 1000)
    private double acceleration;  // m/s^2 (planet gravity)

    // References to child controllers
    private ObjectController oc;
    private PlanetController pc;

    public void setPlanetController(PlanetController controller) {
        this.pc = controller;
    }

    // Legacy method kept for compatibility (updates only the image)
    public void updateImage(Image image) {
        imgView.setImage(image);
    }

    // NEW: update both object image and label in main screen
    public void updateObjectDisplay(String objectName, Image image) {
        imgView.setImage(image);
        if (selectedObjectlbl != null) {
            selectedObjectlbl.setText(objectName != null ? objectName : "No object selected");
        }
    }

    @FXML
    public void initialize() {
        // You can set initial UI state here if needed
        // e.g., selectedObjectlbl.setText("No object selected");
        //       selectedPlanetlbl.setText("No planet selected");
    }

    // close the program
    @FXML
    void closeProgram(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    // open object select screen
    @FXML
    void openObjectMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("objectScreen.fxml"));
        Parent root = loader.load();

        ObjectController oc = loader.getController();
        oc.setMainController(this);  // Inject MainController into ObjectController
        this.oc = oc;

        Stage stage = new Stage();
        stage.setTitle("Object Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // open planet select screen
    @FXML
    void openPlanetMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("planetScreen.fxml"));
        Parent root = loader.load();
        pc = loader.getController();

        // Inject both ways
        this.setPlanetController(pc);   // MainController → PlanetController
        pc.setMainController(this);     // PlanetController → MainController

        Stage stage = new Stage();
        stage.setTitle("Planet Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void handleRun(ActionEvent event) {

        // guard clauses for missing selections
        if ((oc == null || !oc.ready) && (pc == null || !pc.ready)) {
            errorlbl.setText("No objects or planets were selected");
            return;
        }

        if (oc == null || !oc.ready) {
            errorlbl.setText("No objects were selected");
            return;
        }

        if (pc == null || !pc.ready) {
            errorlbl.setText("No planet are selected");
            return;
        }

        // empties error label if there was an error before
        errorlbl.setText(" ");

        // mass in kg: object mass is stored in grams
        mass = oc.getSelectedObject().getMass() / 1000.0;

        // planet acceleration in m/s^2
        acceleration = pc.getSelectedPlanet().getAcceleration();

        // weight = m * g (Newtons)
        weight = mass * acceleration;
        weightLbl.setText(weight + " N");
    }

    // Update planet image and label in main screen (called by PlanetController)
    public void updateSelectedPlanet(String planetName, Image image) {
        planetSelectediv.setImage(image);
        selectedPlanetlbl.setText(planetName);
    }
    
    public void reset() {
        if (imgView != null) imgView.setImage(null);
        if (planetSelectediv != null) planetSelectediv.setImage(null);
        if (selectedPlanetlbl != null) selectedPlanetlbl.setText("No planet selected");
        if (weightLbl != null) weightLbl.setText(" ");
        if (errorlbl != null) errorlbl.setText(" ");
        
    }
      // clears the scale
    @FXML 
    void handleClear(ActionEvent event) {
//        objectImg1.setImage(null);
//        objectImg2.setImage(null);
//        objectImg3.setImage(null);
//        objectImg4.setImage(null);
//        objectImg5.setImage(null);
//        objectCount = 0;
    }
    
    // resets the program (scale, planet, object)
    @FXML
    void handleReset(ActionEvent event) {
        reset();
        
        
    }
    
    // undoes the last action
    @FXML
    void handleUndo(ActionEvent event) {
        
    }
}
