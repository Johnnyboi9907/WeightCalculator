package weightcalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainControllerTest {

    private MainController mainController;

    @Mock
    private PlanetController mockPlanetController;

    @Mock
    private ObjectController mockObjectController;

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {
        });
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController();

        // Manually initialize FXML fields
        mainController.objectImg1 = new ImageView();
        mainController.objectImg2 = new ImageView();
        mainController.objectImg3 = new ImageView();
        mainController.objectImg4 = new ImageView();
        mainController.objectImg5 = new ImageView();
        mainController.imgView = new ImageView();
        mainController.errorlbl = new Label();
        mainController.selectedPlanetlbl = new Label();
        mainController.weightLbl = new Label();
        mainController.planetSelectediv = new ImageView();

        // Set up mocks
        mainController.setPlanetController(mockPlanetController);
        mainController.pc = mockPlanetController;
        mainController.oc = mockObjectController;
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    void testUpdateObjectDisplay_AddsObjectsUpToLimit() {
        Image mockImage = mock(Image.class);

        for (int i = 1; i <= 5; i++) {
            mainController.updateObjectDisplay("Object" + i, mockImage, 100 * i);
            assertEquals(i, mainController.getObjectCount());
        }

        mainController.updateObjectDisplay("Object6", mockImage, 600);
        assertEquals(5, mainController.getObjectCount());
        assertEquals("You can only add up to 5 objects.", mainController.errorlbl.getText());
    }

    @Test
    void testHandleRun_CalculatesWeightCorrectly() {
        Planet mockPlanet = mock(Planet.class);
        when(mockPlanet.getAcceleration()).thenReturn(9.8);
        when(mockPlanetController.getSelectedPlanet()).thenReturn(mockPlanet);
        when(mockPlanetController.ready).thenReturn(true);

        mainController.updateObjectDisplay("Apple", mock(Image.class), 100);
        mainController.updateObjectDisplay("Book", mock(Image.class), 200);

        mainController.handleRun(null);

        assertEquals("2.94 N", mainController.weightLbl.getText());
        assertEquals(" ", mainController.errorlbl.getText());
    }

    @Test
    void testHandleRun_NoObjectsSelected_ShowsError() {
        when(mockPlanetController.ready).thenReturn(true);
        mainController.handleRun(null);
        assertEquals("No objects were added to the scale", mainController.errorlbl.getText());
    }

    @Test
    void testHandleRun_NoPlanetSelected_ShowsError() {
        mainController.updateObjectDisplay("Apple", mock(Image.class), 100);
        when(mockPlanetController.ready).thenReturn(false);
        mainController.handleRun(null);
        assertEquals("No planet are selected", mainController.errorlbl.getText());
    }

    @Test
    void testReset_ClearsAllState() {
        mainController.updateObjectDisplay("Apple", mock(Image.class), 100);
        mainController.updateSelectedPlanet("Earth", mock(Image.class));
        mainController.weightLbl.setText("10.00 N");

        mainController.reset();

        assertEquals(0, mainController.getObjectCount());
        assertNull(mainController.planetSelectediv.getImage());
        assertEquals("No planet selected", mainController.selectedPlanetlbl.getText());
        assertEquals(" ", mainController.weightLbl.getText());
        assertNull(mainController.pc);
    }

    @Test
    void testClear_ClearsObjectsAndLabels() {
        mainController.updateObjectDisplay("Apple", mock(Image.class), 100);
        mainController.weightLbl.setText("10.00 N");

        mainController.clear();

        assertEquals(0, mainController.getObjectCount());
        assertNull(mainController.objectImg1.getImage());
        assertEquals(" ", mainController.weightLbl.getText());
    }

    /**
     * Test of setPlanetController method, of class MainController.
     */
    @Test
    public void testSetPlanetController() {
        System.out.println("setPlanetController");
        PlanetController controller = null;
        MainController instance = new MainController();
        instance.setPlanetController(controller);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateImage method, of class MainController.
     */
    @Test
    public void testUpdateImage() {
        System.out.println("updateImage");
        Image image = null;
        MainController instance = new MainController();
        instance.updateImage(image);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateObjectDisplay method, of class MainController.
     */
    @Test
    public void testUpdateObjectDisplay() {
        System.out.println("updateObjectDisplay");
        String objectName = "";
        Image image = null;
        int massGrams = 0;
        MainController instance = new MainController();
        instance.updateObjectDisplay(objectName, image, massGrams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class MainController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        MainController instance = new MainController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeProgram method, of class MainController.
     */
    @Test
    public void testCloseProgram() {
        System.out.println("closeProgram");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.closeProgram(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openObjectMenu method, of class MainController.
     */
    @Test
    public void testOpenObjectMenu() throws Exception {
        System.out.println("openObjectMenu");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.openObjectMenu(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openPlanetMenu method, of class MainController.
     */
    @Test
    public void testOpenPlanetMenu() throws Exception {
        System.out.println("openPlanetMenu");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.openPlanetMenu(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleRun method, of class MainController.
     */
    @Test
    public void testHandleRun() {
        System.out.println("handleRun");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.handleRun(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSelectedPlanet method, of class MainController.
     */
    @Test
    public void testUpdateSelectedPlanet() {
        System.out.println("updateSelectedPlanet");
        String planetName = "";
        Image image = null;
        MainController instance = new MainController();
        instance.updateSelectedPlanet(planetName, image);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class MainController.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        MainController instance = new MainController();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class MainController.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        MainController instance = new MainController();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleClear method, of class MainController.
     */
    @Test
    public void testHandleClear() {
        System.out.println("handleClear");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.handleClear(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleReset method, of class MainController.
     */
    @Test
    public void testHandleReset() {
        System.out.println("handleReset");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.handleReset(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of undoLastObject method, of class MainController.
     */
    @Test
    public void testUndoLastObject() {
        System.out.println("undoLastObject");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.undoLastObject(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectCount method, of class MainController.
     */
    @Test
    public void testGetObjectCount() {
        System.out.println("getObjectCount");
        MainController instance = new MainController();
        int expResult = 0;
        int result = instance.getObjectCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
