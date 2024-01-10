package com.decafe.tests;


import com.decafe.game.entities.MovementDirection;
import com.decafe.game.entities.Player;
import com.decafe.resources.files.ImageFiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testGetFilenameImageWithoutProduct() {
        Player player = new Player();
        assertEquals(ImageFiles.COFI_BREW_UP, player.getFilenameImageWithoutProduct(),
                "The filename for image without product should match.");
    }

    @Test
    void testGetFilenameImageWithCake() {
        Player player = new Player();
        assertEquals(ImageFiles.COFI_BREW_CAKE_UP, player.getFilenameImageWithCake(),
                "The filename for image with cake should match.");
    }

    @Test
    void testGetMovementSpeed() {
        Player player = new Player();
        assertEquals(Player.DEFAULT_MOVEMENT_SPEED, player.getMovementSpeed(),
                "The default movement speed should match.");
    }

    @Test
    void testSetMovementSpeed() {
        Player player = new Player();
        int newSpeed = 5;
        player.setMovement(newSpeed);
        assertEquals(newSpeed, player.getMovementSpeed(),
                "The movement speed should be updated to the new value.");
    }

    @Test
    void testGetMovementDirection() {
        Player player = new Player();
        assertEquals(MovementDirection.NONE, player.getMovementDirection(),
                "The default movement direction should be NONE.");
    }

    @Test
    void testSetMovementDirection() {
        Player player = new Player();
        MovementDirection newDirection = MovementDirection.UP;
        player.setMovementDirection(newDirection);
        assertEquals(newDirection, player.getMovementDirection(),
                "The movement direction should be updated to the new value.");
    }
}
