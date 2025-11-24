package weightcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class PlanetController {

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
    public void initialize() {
        listView.getItems().addAll("Earth", "Moon", "Mars", "Venus", "Jupiter", "Sun");
    }
}
