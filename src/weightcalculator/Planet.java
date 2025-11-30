/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weightcalculator;

import javafx.scene.image.Image;

/**
 *
 * @author johnn
 */
public class Planet {
    private String name;
    private double acceleration;
    private Image image;
    private Image semiImage;

    public Planet(String name, double acceleration, Image image, Image semiImage) {
        this.name = name;
        this.acceleration = acceleration;
        this.image = image;
        this.semiImage = semiImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public Image getSemiImage() {
        return semiImage;
    }

    public void setSemiImage(Image semiImage) {
        this.semiImage = semiImage;
    }
    
    
}
