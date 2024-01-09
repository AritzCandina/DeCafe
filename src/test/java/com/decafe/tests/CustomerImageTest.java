package com.decafe.tests;

import com.decafe.game.entities.Customer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerImageTest extends ApplicationTest {

    private ImageView[] customerImages;
    private ImageView[] searchArray;

    @Override
    public void start(Stage stage) {
        customerImages = new ImageView[3];
        searchArray = new ImageView[3];

        for (int i = 0; i < customerImages.length; i++) {
            customerImages[i] = new ImageView(new Image("file:customer" + i + ".png"));
            searchArray[i] = new ImageView(new Image("file:search" + i + ".png"));
        }

        Customer.customerImages = customerImages;
    }

    @Test
    public void testGetCorrectCustomerImage_MatchFound() {
        interact(() -> {
            ImageView result = Customer.getCorrectCustomerImage(customerImages[1], searchArray);

            assertEquals(searchArray[1], result, "Should return the corresponding ImageView from searchArray");
        });
    }

    @Test
    public void testGetCorrectCustomerImage_NoMatchFound() {
        interact(() -> {
            ImageView newCustomerImage = new ImageView(new Image("file:newCustomer.png"));

            ImageView result = Customer.getCorrectCustomerImage(newCustomerImage, searchArray);

            assertNotNull(result, "Should return a new ImageView instance");
            assertNotSame(searchArray[0], result, "Returned ImageView should not be in searchArray");
            assertNotSame(searchArray[1], result, "Returned ImageView should not be in searchArray");
            assertNotSame(searchArray[2], result, "Returned ImageView should not be in searchArray");
        });
    }

}
