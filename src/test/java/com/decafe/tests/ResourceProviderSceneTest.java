package com.decafe.tests;

import com.decafe.ApplicationMain;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.Scenes;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;


@ExtendWith(ApplicationExtension.class)
public class ResourceProviderSceneTest extends ApplicationTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Override
    public void start(Stage stage) {
        ApplicationMain.stage = stage;
    }

    @Test
    public void testLoadScene()  {

        Platform.runLater(() -> {
            try {
                ResourceProvider.loadScene(Scenes.START);
                Assertions.assertNotNull(ApplicationMain.stage.getScene());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
