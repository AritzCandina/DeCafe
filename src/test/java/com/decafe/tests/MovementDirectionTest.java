package com.decafe.tests;


import com.decafe.game.entities.MovementDirection;
import com.decafe.resources.files.ImageFiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementDirectionTest {

    @Test
    void testGetCofiBrewImage() {
        assertEquals(ImageFiles.COFI_BREW_LEFT, MovementDirection.LEFT.getCofiBrewImage(),
                "The image for LEFT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_RIGHT, MovementDirection.RIGHT.getCofiBrewImage(),
                "The image for RIGHT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_UP, MovementDirection.UP.getCofiBrewImage(),
                "The image for UP direction should match.");
        assertEquals(ImageFiles.COFI_BREW_DOWN, MovementDirection.DOWN.getCofiBrewImage(),
                "The image for DOWN direction should match.");
        assertNull(MovementDirection.NONE.getCofiBrewImage(),
                "The image for NONE direction should be null.");
    }

    @Test
    void testGetCofiBrewCakeImage() {
        assertEquals(ImageFiles.COFI_BREW_CAKE_LEFT, MovementDirection.LEFT.getCofiBrewCakeImage(),
                "The cake image for LEFT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_CAKE_RIGHT, MovementDirection.RIGHT.getCofiBrewCakeImage(),
                "The cake image for RIGHT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_CAKE_UP, MovementDirection.UP.getCofiBrewCakeImage(),
                "The cake image for UP direction should match.");
        assertEquals(ImageFiles.COFI_BREW_CAKE_DOWN, MovementDirection.DOWN.getCofiBrewCakeImage(),
                "The cake image for DOWN direction should match.");
        assertNull(MovementDirection.NONE.getCofiBrewCakeImage(),
                "The cake image for NONE direction should be null.");
    }

    @Test
    void testGetCofiBrewCoffeeImage() {
        assertEquals(ImageFiles.COFI_BREW_COFFEE_LEFT, MovementDirection.LEFT.getCofiBrewCoffeeImage(),
                "The coffee image for LEFT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_COFFEE_RIGHT, MovementDirection.RIGHT.getCofiBrewCoffeeImage(),
                "The coffee image for RIGHT direction should match.");
        assertEquals(ImageFiles.COFI_BREW_COFFEE_UP, MovementDirection.UP.getCofiBrewCoffeeImage(),
                "The coffee image for UP direction should match.");
        assertEquals(ImageFiles.COFI_BREW_COFFEE_DOWN, MovementDirection.DOWN.getCofiBrewCoffeeImage(),
                "The coffee image for DOWN direction should match.");
        assertNull(MovementDirection.NONE.getCofiBrewCoffeeImage(),
                "The coffee image for NONE direction should be null.");
    }
}

