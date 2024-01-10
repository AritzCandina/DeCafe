package com.decafe.tests;

import com.decafe.game.entities.MovementVector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovementVectorTest {

    @Test
    void testMovementVectorConstructor() {
        double x = 5.0;
        double y = 10.0;

        MovementVector vector = new MovementVector(x, y);

        assertEquals(x, vector.getX(), "The X coordinate is not set correctly in the constructor.");
        assertEquals(y, vector.getY(), "The Y coordinate is not set correctly in the constructor.");
    }
}
