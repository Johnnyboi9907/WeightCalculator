import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class ObjectController {

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
        listView.getItems().addAll("Apple", "Pile of books", "Gold bar", "Human", "Gorilla", "Bugatti Chiron");
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