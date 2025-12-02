package weightcalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
}
