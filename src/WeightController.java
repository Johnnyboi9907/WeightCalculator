/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2483268
 */
public class WeightController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private MenuItem undoBtn;

    @FXML
    private AnchorPane scenePane;
    
    @FXML
    private Label weightLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

// close the program
    @FXML
    void closeProgram(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

}
