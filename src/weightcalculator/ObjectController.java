package weightcalculator;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    private String[] objectStrs;
    private String selectedObject;
    private Object[] objects;
    private MainController mc;
    
    public void setMainController(MainController main) {
        this.mc = main;
    }

    @FXML
    public void initialize() {
        Object apple = new Object("Apple", 100, new Image("images/apple.jpg"));
        Object books = new Object("Pile of books", 700, new Image("images/books.png"));
        Object gold = new Object("Gold bar", 1000, new Image("images/goldbar.png"));
        Object human = new Object("Human", 70000, new Image("images/person.png"));
        Object gorilla = new Object("Gorilla", 160000, new Image("images/gorilla.png"));
        Object car = new Object("Bugatti Chiron", 2000000, new Image("images/car.png"));

        objectStrs = new String[]{"Apple", "Pile of books", "Gold bar", "Human", "Gorilla", "Bugatti Chiron"};
        objects = new Object[]{apple, books, gold, human, gorilla, car};

        listView.getItems().addAll(objectStrs);
        //listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {

                if (newVal == null) {
                    return;
                }

                switch (newVal) {
                    case "Apple":
                        nameLbl.setText(apple.getName());
                        massLbl.setText("Mass = " + String.valueOf(apple.getMass()) + " g");
                        imgView.setImage(apple.getImage());
                        addBtn.setDisable(false);
                        break;

                    case "Pile of books":
                        nameLbl.setText(books.getName());
                        massLbl.setText("Mass = " + String.valueOf(books.getMass()) + " g");
                        imgView.setImage(books.getImage());
                        addBtn.setDisable(false);
                        break;

                    case "Gold bar":
                        nameLbl.setText(gold.getName());
                        massLbl.setText("Mass = " + String.valueOf(gold.getMass()) + " g");
                        imgView.setImage(gold.getImage());
                        addBtn.setDisable(false);
                        break;

                    case "Human":
                        nameLbl.setText(human.getName());
                        massLbl.setText("Mass = " + String.valueOf(human.getMass()) + " g");
                        imgView.setImage(human.getImage());
                        addBtn.setDisable(false);
                        break;

                    case "Gorilla":
                        nameLbl.setText(gorilla.getName());
                        massLbl.setText("Mass = " + String.valueOf(gorilla.getMass()) + " g");
                        imgView.setImage(gorilla.getImage());
                        addBtn.setDisable(false);
                        break;

                    case "Bugatti Chiron":
                        nameLbl.setText(car.getName());
                        massLbl.setText("Mass = " + String.valueOf(car.getMass()) + " g");
                        imgView.setImage(car.getImage());
                        addBtn.setDisable(false);
                        break;
                }
            }
        });
    }

    @FXML
    void handleAdd(ActionEvent event) throws IOException {
        String selected = listView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < objectStrs.length; i++) {
            if (objectStrs[i].equals(selected)) {
                Image image = objects[i].getImage();
                mc.updateImage(image);
            }
        }
        // find the object of the chosen list item
        // then take its image and display it on the main screen

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
