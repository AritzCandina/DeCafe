package com.decafe.tests;

import com.decafe.game.entities.Customer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerSpawnTest extends ApplicationTest {

    private static final int MAX_NUMBER_CUSTOMERS = 3;
    private ImageView[] customerImages;
    private ImageView[] smileyImages;
    private ImageView[] coinImages;
    private ImageView[] orderLabels;

    @Before
    public void setUp() {
        Customer.customersInCoffeeShop = new ArrayList<>();
        Customer.allCustomers = new ArrayList<>();
        Customer.freeChairs = new ArrayList<>();
        Customer.customerImages = customerImages;
        Customer.smileyImages = smileyImages;
        Customer.coinImages = coinImages;
        Customer.orderLabels = orderLabels;
    }

    @Override
    public void start(Stage stage) {
        customerImages = new ImageView[MAX_NUMBER_CUSTOMERS];
        smileyImages = new ImageView[MAX_NUMBER_CUSTOMERS];
        coinImages = new ImageView[MAX_NUMBER_CUSTOMERS];
        orderLabels = new ImageView[MAX_NUMBER_CUSTOMERS];

        for (int i = 0; i < MAX_NUMBER_CUSTOMERS; i++) {
            customerImages[i] = new ImageView(new Image("file:customer" + i + ".png"));
            smileyImages[i] = new ImageView(new Image("file:smiley" + i + ".png"));
            coinImages[i] = new ImageView(new Image("file:coin" + i + ".png"));
            orderLabels[i] = new ImageView(new Image("file:orderLabel" + i + ".png"));  // Initialize each ImageView in orderLabels
        }
    }
    @Test
    public void testSpawnCustomers() {
        Customer.freeChairs.add(1);

        Customer.spawnCustomers();

        assertEquals("One customer should be added to customersInCoffeeShop", 1, Customer.customersInCoffeeShop.size());
        assertEquals("One customer should be added to allCustomers", 1, Customer.allCustomers.size());

    }

}
