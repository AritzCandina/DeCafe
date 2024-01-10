package com.decafe.tests;

import com.decafe.game.entities.Customer;
import com.decafe.game.entities.SmileyColor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class CustomerTest extends ApplicationTest {

    private ImageView mockImageView;
    private ImageView mockCoinImage;
    private Customer customer;

    @Override
    public void start(Stage stage) {
        mockImageView = new ImageView();
        mockCoinImage = new ImageView();
        customer = new Customer();
        customer.coinImage = mockCoinImage;
        Customer.freeChairs = new ArrayList<>();
        Customer.setSpawnTimer(new Timer());

        Scene scene = new Scene(new Group(mockImageView, mockCoinImage), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testLeaveWhenUnhappy() {
       interact(() -> {
            customer.leftUnhappy = true;
            try {
                customer.leave(mockImageView);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            assertFalse(mockImageView.isVisible());
            assertTrue(mockCoinImage.isVisible());
            assertFalse(mockCoinImage.isDisabled());
        });
    }

    @Test
    public void testNoMoneySpent() {
        interact(() -> {
            customer.coinImage.setVisible(true);
            customer.coinImage.setDisable(false);
            int initialChairCount = 0;
            int chairNumber = 5;
            customer.setChair(chairNumber);

            try {
                Customer.noMoneySpent(customer);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            assertFalse(customer.coinImage.isVisible());
            assertTrue(customer.coinImage.isDisabled());
            assertTrue(Customer.freeChairs.contains(chairNumber));
            assertEquals(initialChairCount + 1, Customer.freeChairs.size());
        });
    }

    @Test
    void addFreeSeatTest() {
        int chairNumber = 5;
        Customer.addFreeSeat(chairNumber);
        assertTrue(Customer.freeChairs.contains(chairNumber));
    }

    @Test
    void isGreenTest() {
        customer.setSmileyColor(SmileyColor.GREEN);
        assertTrue(customer.isGreen());
        assertFalse(customer.isRed());
        assertFalse(customer.isYellow());
    }

    @Test
    void isRedTest() {
        customer.setSmileyColor(SmileyColor.RED);
        assertTrue(customer.isRed());
        assertFalse(customer.isGreen());
        assertFalse(customer.isYellow());
    }

    @Test
    void isYellowTest() {
        customer.setSmileyColor(SmileyColor.YELLOW);
        assertTrue(customer.isYellow());
        assertFalse(customer.isGreen());
        assertFalse(customer.isRed());
    }



}
