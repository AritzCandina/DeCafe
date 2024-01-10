package com.decafe.tests;

import com.decafe.game.entities.Customer;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class CustomerConstructorTest extends ApplicationTest {

    private ImageView mockImage;
    private ImageView mockLabel;
    private ImageView mockSmiley;
    private ImageView mockCoinImage;
    private int chairNumber;

    @Override
    public void start(Stage stage) {
        mockImage = new ImageView();
        mockLabel = new ImageView();
        mockSmiley = new ImageView();
        mockCoinImage = new ImageView();
        chairNumber = 5;
    }

    @Test
    public void testCustomerConstructor() {
        Customer customer = new Customer(mockImage, mockLabel, chairNumber, mockSmiley, mockCoinImage);

        assertEquals(mockImage, customer.getCorrectCustomerImage());
        assertEquals(mockLabel, customer.getCorrectCustomerOrderLabel());
        assertEquals(chairNumber, customer.getChair());
        assertEquals(mockCoinImage, customer.getCoinImage());
        assertFalse(customer.isAlreadyOrdered());
        assertNotNull(customer.getSixtySecondsTimer());
    }

}
