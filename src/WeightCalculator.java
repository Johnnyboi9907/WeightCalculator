/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 2483268
 * 
 * mass of an apple = 100g
 * mass of a pile of books = 700g
 * mass of a gold bar = 1000g
 * mass of a human = 70 kg = 70000g
 * mass of a gorilla = 160kg = 160000g
 * mass of a bugatti chiron = 2000kg = 2000000g
 */
public class WeightCalculator extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
