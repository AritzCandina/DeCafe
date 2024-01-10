package com.decafe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.decafe.AppController;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;

public class AppControllerButtonsTest extends ApplicationTest {

    private AppController appController;
    private ImageView startButton, startQuitButton, gameStartButton, playAgainImage, backToStartImage, quitEndScreenImage;


    @BeforeEach
    void setUp() {
        // Initialize the AppController and ImageView
        appController = new AppController();
        startButton = new ImageView();
        startQuitButton = new ImageView();
        gameStartButton = new ImageView();
        playAgainImage = new ImageView();
        backToStartImage = new ImageView();
        quitEndScreenImage = new ImageView();

        appController.startButton = startButton;
        appController.startQuitButton = startQuitButton;
        appController.gameStartButton = gameStartButton;
        appController.playAgainImage = playAgainImage;
        appController.backToStartImage = backToStartImage;
        appController.quitEndScreenImage = quitEndScreenImage;
    }

    @Test
    void changeStartCoffeeImage() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.START_COFFEE_HOT);
        mockImageChange(image, () -> {
            try {
                appController.changeStartCoffeeImage();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, startButton);
    }

    @Test
    void changeStartCoffeeImageBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.START_COFFEE);
        mockImageChange(image, () -> {
            try {
                appController.changeStartCoffeeImageBack();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, startButton);
    }


    @Test
    void changeQuitStartScreenTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.QUITE_END_SCREEN_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changeQuitStartScreen();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, startQuitButton);
    }

    @Test
    void changeQuitStartScreenBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.QUIT_END_SCREEN);
        mockImageChange(image, () -> {
            try {
                appController.changeQuitStartScreenBack();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, startQuitButton);
    }


    @Test
    void changeStartImageTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.INSTRUCTIONS_GOT_IT);
        mockImageChange(image, () -> {
            try {
                appController.changeStartImage();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, gameStartButton);
    }

    @Test
    void changeStartImageBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.INSTRUCTIONS_GOT_IT_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changeStartImageBack();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, gameStartButton);
    }

    @Test
    void changePlayAgainTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.PLAY_AGAIN_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changePlayAgain();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, playAgainImage);
    }

    @Test
    void changePlayAgainBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.PLAY_AGAIN);
        mockImageChange(image, () -> {
            try {
                appController.changePlayAgainBack();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, playAgainImage);
    }

    @Test
    void changeBackToStartMenuTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.BACK_TO_START_MENU_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changeBackToStartMenu();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, backToStartImage);
    }

    @Test
    void changeBackToStartMenuBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.QUITE_END_SCREEN_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changeBackToStartMenu();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, backToStartImage);
    }

    @Test
    void changeQuitEndScreenTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.QUITE_END_SCREEN_BRIGHTER);
        mockImageChange(image, () -> {
            try {
                appController.changeQuitEndScreen();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, quitEndScreenImage);
    }

    @Test
    void changeQuitEndScreenBackTest() throws FileNotFoundException {
        Image image = ResourceProvider.createImage(ImageFiles.QUIT_END_SCREEN);
        mockImageChange(image, () -> {
            try {
                appController.changeQuitEndScreenBack();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, quitEndScreenImage);
    }



    private void mockImageChange(Image image, Runnable methodUnderTest, ImageView imageView) throws FileNotFoundException {
        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(anyString())).thenReturn(image);
            methodUnderTest.run();
            assertEquals(image, imageView.getImage());
        }
    }
}
