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
 * @author 2483268
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
    private ImageView imgView;
    
    @FXML
    private Label errorlbl;
    
    @FXML
    private Label selectedPlanetlbl;

    @FXML
    private PlanetController planetPaneController;  
    
    @FXML
    private ImageView planetSelectediv;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private double weight;
    private double mass;
    private double acceleration;
    private ObjectController oc;
    private PlanetController pc;

    public void setPlanetController(PlanetController controller) {
        this.pc = controller;
    }

    public void updateImage(Image image) {
        imgView.setImage(image);
    }

    @FXML
    public void initialize() {

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
        oc.setMainController(this);
        this.oc = oc;

        Stage stage = new Stage();
        //stage = (Stage) menuBar.getScene().getWindow();
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
        this.setPlanetController(pc);     // MainController → PlanetController
        pc.setMainController(this);       // PlanetController → MainController ✅

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }




    @FXML
    void handleRun(ActionEvent event) {

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

        mass = oc.getSelectedObject().getMass() / 1000;
        acceleration = pc.getSelectedPlanet().getAcceleration();

        weight = mass * acceleration;
        weightLbl.setText(weight + " N");
    }
    public void updateSelectedPlanet(String planetName, Image image) {
        planetSelectediv.setImage(image);
        selectedPlanetlbl.setText(planetName);
    }
    
    


}
