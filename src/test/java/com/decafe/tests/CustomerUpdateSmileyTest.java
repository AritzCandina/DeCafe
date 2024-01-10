package com.decafe.tests;

import com.decafe.game.entities.Customer;
import com.decafe.game.entities.SmileyColor;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.decafe.game.entities.Customer.CUSTOMER_WAITING_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerUpdateSmileyTest {

    @Mock
    private ImageView smiley;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(null, null, 0, smiley, null);
    }

    @Test
    void testUpdateSmileyStateGreen() {
        customer.updateSmileyState(CUSTOMER_WAITING_TIME - 1);
        assertEquals(SmileyColor.GREEN, customer.getSmileyColor(), "Smiley color should be GREEN");
    }

    @Test
    void testUpdateSmileyStateYellow() {
        customer.updateSmileyState(CUSTOMER_WAITING_TIME / 2);
        assertEquals(SmileyColor.YELLOW, customer.getSmileyColor(), "Smiley color should be YELLOW");
    }

    @Test
    void testUpdateSmileyStateRed() {
        customer.updateSmileyState(CUSTOMER_WAITING_TIME / 4);
        assertEquals(SmileyColor.RED, customer.getSmileyColor(), "Smiley color should be RED");
    }
}