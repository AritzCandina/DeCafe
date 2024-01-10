package com.decafe.tests;

import com.decafe.game.entities.Upgrade;
import com.decafe.resources.files.ImageFiles;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UpgradeTest extends ApplicationTest {

    private Upgrade upgrade;
    private ImageView imageView;
    private ImageView imageViewMock = Mockito.mock(ImageView.class);

    private String filenameUpgradeNotUsed = "upgradeSkates.png";
    private String filenameUpgradeUsed = "upgradeSkatesUsed.png";

    @Override
    public void start(Stage stage) {
        imageView = new ImageView();


        upgrade = new Upgrade(10, false, ImageFiles.UPGRADE_SKATES, ImageFiles.UPGRADE_SKATES_USED, imageView);


        stage.show();
    }

    @BeforeEach
    void setUp() throws TimeoutException {
        // Launch the JavaFX application
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new javafx.application.Application() {
            @Override
            public void start(Stage primaryStage) {
            }
        });
    }

    @Test
    void testDoUpgradeWhenNotUsedOnce() throws FileNotFoundException {
        int initialCoins = 20;

        int newCoins = upgrade.doUpgrade(initialCoins);

        assertEquals(10, newCoins);
        assertTrue(upgrade.isAlreadyUsedOnce());
    }



    @Override
    public void stop() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @Test
    void testGetFilenameUpgradeUsed() {
        when(imageViewMock.getImage()).thenReturn(null);

        String result = upgrade.getFilenameUpgradeUsed();
        assertEquals(filenameUpgradeUsed, result);
    }

    @Test
    void testGetFilenameUpgradeNotUsed() {
        when(imageViewMock.getImage()).thenReturn(null);

        String result = upgrade.getFilenameUpgradeNotUsed();
        assertEquals(filenameUpgradeNotUsed, result);
    }


    @Test
    void testGetUpgradeImageView() {
        ImageView expectedImageView = new ImageView();
        Upgrade upgrade = new Upgrade(10, false, "upgradeNotUsed.png", "upgradeUsed.png", expectedImageView);

        ImageView actualImageView = upgrade.getUpgradeImageView();

        assertEquals(expectedImageView, actualImageView, "The returned ImageView should be the same as the one set in the constructor.");
    }

}
