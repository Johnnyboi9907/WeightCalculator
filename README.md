# Weight Calculator

**Description**
A JavaFX application that calculates the combined weight (in Newtons) of up to five objects on a selected planet. The program converts object masses (grams) to kilograms and multiplies by the planet's gravitational acceleration to compute weight.

**How to run**
1. Ensure you have Java 17+ and JavaFX SDK installed.
2. Open the project in NetBeans (or any IDE).
3. Resources (images) must be available under `/images/` on the classpath.
4. Run `WeightCalculator` (the `main` class) which loads `mainScreen.fxml`.
5. Use the "Select → Object Select" and "Select → Planet Select" menus to choose objects and a planet, then use `File → Run` to calculate the weight.

**Features**
- Predefined objects: Apple (100 g), Pile of books (700 g), Gold bar (1000 g), Human (70,000 g), Gorilla (160,000 g), Bugatti Chiron (2,000,000 g)
- Custom object with mass slider/text input (1 g — 1,000,000 g)
- Planet selection with gravity accelerations (Earth, Moon, Mars, Venus, Jupiter, Sun)
- Add up to 5 objects to the scale
- Undo last object, Clear scale, Reset program

**Project structure**
```
weightcalculator/
├─ MainController.java
├─ ObjectController.java
├─ PlanetController.java
├─ Object.java
├─ Planet.java
├─ WeightCalculator.java
├─ mainScreen.fxml
├─ objectScreen.fxml
├─ planetScreen.fxml
├─ styles.css
└─ images/...
```

**Teamwork**
- THEO GABRIAL: 
Fixed logic in code
Implemented the ability for the scale to have multiple objects
Created the custom object
Adding or selecting an object or planet would return to the main screen
Created alerts for the custom object
Fixed error for if no object or planet is selected
Junit Testing (Wasn’t able to be implemented properly but tried his best)
Menu buttons (Undo, Clear scale, Reset)
Implemented the scale being on the planet that is selected in the main screen



**Notes**
- Images referenced via `getResourceAsStream("/images/...")` must exist in resources.
- UI styles are in `styles.css`.

