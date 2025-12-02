package weightcalculator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    MenuItem clearBtn;
    @FXML
    MenuItem closeBtn;
    @FXML
    MenuItem objectBtn;
    @FXML
    MenuItem planetBtn;
    @FXML
    MenuItem resetBtn;
    @FXML
    MenuItem runBtn;
    @FXML
    ImageView objectImg1;
    @FXML
    ImageView objectImg2;
    @FXML
    ImageView objectImg3;
    @FXML
    ImageView objectImg4;
    @FXML
    ImageView objectImg5;
    @FXML
    AnchorPane scenePane;
    @FXML
    MenuItem undoBtn;
    @FXML
    Label weightLbl;
    @FXML
    MenuBar menuBar;
    @FXML
    ImageView imgView;              // image of selected object
    @FXML
    Label errorlbl;                 // error messages
    @FXML
    Label selectedPlanetlbl;        // name of selected planet
    @FXML
    PlanetController planetPaneController;
    @FXML
    ImageView planetSelectediv;     // image of selected planet

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private int objectCount = 0;

    // Physics values
    private double weight;
    private double mass;          // in kg (object mass in grams / 1000)
    private double acceleration;  // m/s^2 (planet gravity)

    // References to child controllers
    ObjectController oc;
    PlanetController pc;

    public void setPlanetController(PlanetController controller) {
        this.pc = controller;
    }

    // Legacy method kept for compatibility (updates only the image)
    public void updateImage(Image image) {
        imgView.setImage(image);
    }

    private final List<Integer> massesOnScale = new ArrayList<>(); // grams

// Called by ObjectController whenever an object is added
    public void updateObjectDisplay(String objectName, Image image, int massGrams) {
        // Place image into next slot (max 5)
        if (image != null && objectCount < 5) {
            switch (objectCount) {
                case 0:
                    objectImg1.setImage(image);
                    break;
                case 1:
                    objectImg2.setImage(image);
                    break;
                case 2:
                    objectImg3.setImage(image);
                    break;
                case 3:
                    objectImg4.setImage(image);
                    break;
                case 4:
                    objectImg5.setImage(image);
                    break;
            }
            objectCount++;
            massesOnScale.add(massGrams); // track mass for calculation
        } else if (objectCount >= 5) {
            errorlbl.setText("You can only add up to 5 objects.");
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
        if (massesOnScale.isEmpty() && (pc == null || !pc.ready)) {
            errorlbl.setText("No objects or planets were selected");
            return;
        }

        if (massesOnScale.isEmpty()) {
            errorlbl.setText("No objects were added to the scale");
            return;
        }

        if (pc == null || !pc.ready) {
            errorlbl.setText("No planet are selected");
            return;
        }

        // empties error label if there was an error before
        errorlbl.setText(" ");

        // Sum mass in kg
        double totalMassKg = 0.0;
        for (int m : massesOnScale) {
            totalMassKg += m / 1000.0;
        }

        double acceleration = pc.getSelectedPlanet().getAcceleration(); // m/s^2
        double weight = totalMassKg * acceleration; // Newtons
        // %.2f makes it 2 deigits after decimal point
        weightLbl.setText(String.format("%.2f N", weight));
    }

    

    // Update planet image and label in main screen (called by PlanetController)
    public void updateSelectedPlanet(String planetName, Image image) {
        planetSelectediv.setImage(image);
        selectedPlanetlbl.setText(planetName);
    }
    
    public void reset() {
        // Reuse clear to ensure objects/images/list are cleaned
        clear();

        // Clear planet visuals + label
        if (planetSelectediv != null) {
            planetSelectediv.setImage(null);
        }
        if (selectedPlanetlbl != null) {
            selectedPlanetlbl.setText("No planet selected");
        }

        // Reset planet controller reference so user must reselect
        if (pc != null) {
            try {
                pc.ready = false;
            } catch (Exception ignored) {
            }
            pc = null;
        }

        // Also reset object controller reference if you want fresh state
        if (oc != null) {
            try {
                oc.ready = false;
            } catch (Exception ignored) {
            }
            oc = null;
        }

        // Ensure weight/error labels are blank
        if (weightLbl != null) {
            weightLbl.setText(" ");
        }
        if (errorlbl != null) {
            errorlbl.setText(" ");
        }
    }
    
    public void clear() {

        if (objectImg1 != null) {
            objectImg1.setImage(null);
        }
        if (objectImg2 != null) {
            objectImg2.setImage(null);
        }
        if (objectImg3 != null) {
            objectImg3.setImage(null);
        }
        if (objectImg4 != null) {
            objectImg4.setImage(null);
        }
        if (objectImg5 != null) {
            objectImg5.setImage(null);
        }

        objectCount = 0;
        massesOnScale.clear();      // important: remove stored masses

        // clear displayed single object image if used
        if (imgView != null) {
            imgView.setImage(null);
        }
        if (weightLbl != null) {
            weightLbl.setText(" ");
        }
        if (errorlbl != null) {
            errorlbl.setText(" ");
        }

        // reset internal physics values
        weight = 0;
        mass = 0;
        acceleration = 0;

        // don't null planet controller here (reset() will do it) — but if you want to force reselect, uncomment:
        // if (oc != null) { try { oc.ready = false; } catch(Exception ignored) {} oc = null; }
    }

      // clears the scale
    @FXML 
    void handleClear(ActionEvent event) {
        clear();
    }
    
    // resets the program (scale, planet, object)
    @FXML
    void handleReset(ActionEvent event) {
        reset();
        
        
    }
    
    // undoes the last action
    @FXML
    void handleUndo(ActionEvent event) {
        if (objectCount == 0) {
            errorlbl.setText("No objects to undo.");
            return;
        }

        // Remove last image
        switch (objectCount - 1) {
            case 0:
                objectImg1.setImage(null);
                break;
            case 1:
                objectImg2.setImage(null);
                break;
            case 2:
                objectImg3.setImage(null);
                break;
            case 3:
                objectImg4.setImage(null);
                break;
            case 4:
                objectImg5.setImage(null);
                break;
        }

        // Remove last mass
        if (!massesOnScale.isEmpty()) {
            massesOnScale.remove(massesOnScale.size() - 1);
        }

        objectCount--;

        // Recalculate weight
        double totalMassKg = 0.0;
        for (int m : massesOnScale) {
            totalMassKg += m / 1000.0;
        }

        if (pc != null && pc.ready) {
            double acceleration = pc.getSelectedPlanet().getAcceleration();
            double weight = totalMassKg * acceleration;
            weightLbl.setText(String.format("%.2f N", weight));
        } else {
            weightLbl.setText("0 N");
        }

        errorlbl.setText("Last object removed.");
    }
    
    public int getObjectCount() {
        return objectCount;
    }
}
