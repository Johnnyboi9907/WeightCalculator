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
THEO GABRIAL: 
- Implemented the ability for the scale to display and calculate the weight of multiple objects (maximum 5 objects)
- Created the custom object
- Adding an object or selecting a planet would return to the main screen automatically
- Created alerts for the custom object (invalid mass number, mass number too large)
- Error catching (no selected planet or objects will result in the program displaying a warning message)
- Junit Testing (unsuccessful, but tried his best)
- Event handling for Undo, Clear scale, and Reset buttons in the Menu option of the menu bar
- Displayed an image of the currently selected planet in the main screen
- Fixed and overlooked the semantics and syntax of the code

JOHN ALEXIOU:
- GUI design for main screen, planet screen and object screen (using scenebuilder)
- Created the controller classes for the object screen and the planet screen and connected both to their respective fxml files
- Implemented and displayed for both controller classes a listview containing all available planets or objects to chose from
- Added a changeListener to the listview which updates the attribute labels and the image depending on the selected object or planet
- Wrote all the logic involving the calculation of the weight, and made the total weight appear on a label
- Handled opening object selection menu and planet selection menu in the Select option of the menu bar
- Selecting an object in the object menu will display the image of it on top of the scale in the main menu
- Created the images resource folder and added all the images inside, including all object images and planet images

DAVID AYOUB:
- Created the planet and object classes along with the proper attributes & methods
- Created the main screen controller


**Notes**
- Images referenced via `getResourceAsStream("/images/...")` must exist in resources.
- UI styles are in `styles.css`.
