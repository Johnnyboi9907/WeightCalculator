package weightcalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ObjectControllerTest {

    private ObjectController objectController;

    @Mock
    private MainController mockMainController;

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {
        });
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectController = new ObjectController();

        // Manually initialize FXML fields
        objectController.listView = new ListView<>();
        objectController.nameLbl = new Label();
        objectController.massLbl = new Label();
        objectController.imgView = new ImageView();
        objectController.addBtn = new Button();
        objectController.customMassSlider = new Slider();
        objectController.customMasstf = new TextField();
        objectController.customMasslbl = new Label();

        objectController.setMainController(mockMainController);
        objectController.initialize();
    }

    @Test
    void testInitialize_SetsUpObjectsAndList() {
        assertEquals(7, objectController.listView.getItems().size());
        assertTrue(objectController.listView.getItems().contains("Apple"));
        assertFalse(objectController.ready);
        assertFalse(objectController.customMassSlider.isVisible());
    }

    @Test
    void testHandleAdd_PredefinedObject_AddsToMain() {
        when(mockMainController.getObjectCount()).thenReturn(0);
        objectController.listView.getSelectionModel().select("Apple");
        objectController.setObjectDisplay(objectController.objects[0]);
        objectController.handleAdd(null);

        assertTrue(objectController.ready);
        verify(mockMainController).updateObjectDisplay(eq("Apple"), any(Image.class), eq(100));
    }

    @Test
    void testHandleAdd_CustomObject_AddsWithMass() {
        when(mockMainController.getObjectCount()).thenReturn(0);
        objectController.listView.getSelectionModel().select("Custom");

        objectController.nameLbl.setText("Custom Object");
        objectController.customMassSlider.setValue(1000);
        objectController.customMasstf.setText("500");
        objectController.massLbl.setText("Mass = 500 g");
        objectController.customMassSlider.setVisible(true);
        objectController.customMasstf.setVisible(true);
        objectController.customMasslbl.setVisible(true);
        objectController.addBtn.setDisable(false);

        objectController.handleAdd(null);

        assertTrue(objectController.ready);
        verify(mockMainController).updateObjectDisplay(eq("Custom Object"), any(Image.class), eq(500));
    }

    @Test
    void testHandleAdd_ExceedsLimit_ShowsAlert() {
        when(mockMainController.getObjectCount()).thenReturn(5);
        objectController.listView.getSelectionModel().select("Apple");
        objectController.setObjectDisplay(objectController.objects[0]);
        objectController.handleAdd(null);

        verify(mockMainController, never()).updateObjectDisplay(anyString(), any(Image.class), anyInt());
    }

    @Test
    void testCustomMassSlider_UpdatesTextFieldAndLabels() {
        objectController.customMassSlider.setValue(750);
        objectController.customMassSlider.valueProperty().set(750);

        assertEquals("750", objectController.customMasstf.getText());
        assertEquals("750 g", objectController.customMasslbl.getText());
        assertEquals("Mass = 750 g", objectController.massLbl.getText());
    }
}
