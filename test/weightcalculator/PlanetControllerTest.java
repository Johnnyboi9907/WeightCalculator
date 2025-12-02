package weightcalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlanetControllerTest {

    private PlanetController planetController;

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
        planetController = new PlanetController();

        // Manually initialize FXML fields
        planetController.listView = new ListView<>();
        planetController.nameLbl = new Label();
        planetController.accelerationLbl = new Label();
        planetController.imgView = new ImageView();
        planetController.selectBtn = new Button();

        planetController.setMainController(mockMainController);
        planetController.initialize();
    }


@Test
    void testInitialize
