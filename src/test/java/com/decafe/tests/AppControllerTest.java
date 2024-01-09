package com.decafe.tests;

import com.decafe.AppController;
import com.decafe.game.Game;
import com.decafe.game.entities.MovementVector;
import com.decafe.game.entities.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
public class AppControllerTest extends ApplicationTest {

    private AppController appController;
    private Game game;
    private Player player;
    private Label coinsEarnedLabel;
    private BooleanProperty wPressed;
    private BooleanProperty sPressed;
    private BooleanProperty aPressed;
    private BooleanProperty dPressed;



    @Override
    public void start(Stage stage) {
        game = Mockito.mock(Game.class);
        player = Mockito.mock(Player.class);
        coinsEarnedLabel = Mockito.mock(Label.class);
        ImageView playerUpgradeImageView = Mockito.mock(ImageView.class);
        ImageView waiterImageView = Mockito.mock(ImageView.class);

        wPressed = new SimpleBooleanProperty(false);
        sPressed = new SimpleBooleanProperty(false);
        aPressed = new SimpleBooleanProperty(false);
        dPressed = new SimpleBooleanProperty(false);

        appController = new AppController();
        appController.game = game;
        appController.player = player;
        appController.coinsEarnedLabel = coinsEarnedLabel;
        appController.waiterImageView = waiterImageView;
        appController.upgradePlayerImageView = playerUpgradeImageView;

        appController.wPressed = wPressed;
        appController.sPressed = sPressed;
        appController.aPressed = aPressed;
        appController.dPressed = dPressed;
    }


    @Test
    public void testDoUpgrade() throws FileNotFoundException {
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);
        ImageView sourceImageView = Mockito.mock(ImageView.class);
        when(mockEvent.getSource()).thenReturn(sourceImageView);
        when(sourceImageView.getId()).thenReturn("someUpgradeId");
        when(game.getCoinsEarned()).thenReturn(50);

        appController.doUpgrade(mockEvent);

        Mockito.verify(game).doUpgrade("someUpgradeId", player);
        Mockito.verify(coinsEarnedLabel).setText("50");
        Mockito.verify(game).checkUpgradePossible(game.getCoffeeUpgrade());
        Mockito.verify(game).checkUpgradePossible(game.getCakeUpgrade());
        Mockito.verify(game).checkUpgradePossible(game.getPlayerUpgrade());
    }


    @Test
    public void testKeyPressed() {
        KeyEvent mockEvent = Mockito.mock(KeyEvent.class);
        when(mockEvent.getCode()).thenReturn(KeyCode.W);

        appController.keyPressed(mockEvent);

        assertTrue(appController.wPressed.get());
    }

    @Test
    public void testKeyReleased() {
        KeyEvent mockEvent = Mockito.mock(KeyEvent.class);
        when(mockEvent.getCode()).thenReturn(KeyCode.W);

        appController.wPressed.set(true);
        appController.keyReleased(mockEvent);

        assertFalse(appController.wPressed.get());
    }

    @Test
    public void testDetermineNewMovementVectorNoKeysPressed() {
        double moveDistance = 10.0;
        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(0.0, result.getX(),0.001);
        assertEquals(0.0, result.getY(),0.001);
    }

    @Test
    public void testDetermineNewMovementVectorWPressed() {

        double moveDistance = 10.0;
        wPressed.set(true);

        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(0.0, result.getX(), 0.001);
        assertEquals(-moveDistance, result.getY(), 0.001);
    }

    @Test
    public void testDetermineNewMovementVectorSPressed() {
        double moveDistance = 10.0;
        sPressed.set(true);

        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(0.0, result.getX(), 0.001);
        assertEquals(moveDistance, result.getY(), 0.001);
    }

    @Test
    public void testDetermineNewMovementVectorAPressed() {
        double moveDistance = 10.0;
        aPressed.set(true);

        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(-moveDistance, result.getX(), 0.001);
        assertEquals(0.0, result.getY(), 0.001);
    }

    @Test
    public void testDetermineNewMovementVectorDPressed() {
        double moveDistance = 10.0;
        dPressed.set(true);

        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(moveDistance, result.getX(), 0.001);
        assertEquals(0.0, result.getY(), 0.001);
    }

    @Test
    public void testDetermineNewMovementVectorMultipleKeysPressed() {
        double moveDistance = 10.0;
        wPressed.set(true);
        aPressed.set(true);
        dPressed.set(true);

        MovementVector result = appController.determineNewMovementVector(moveDistance);
        assertEquals(moveDistance, result.getX(), 0.001);
        assertEquals(-moveDistance, result.getY(), 0.001);
    }




}
