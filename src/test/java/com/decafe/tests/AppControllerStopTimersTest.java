package com.decafe.tests;


import com.decafe.AppController;
import com.decafe.game.entities.Customer;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppControllerStopTimersTest {

    private AppController appController;

    @Mock
    private Player player;
    @Mock
    private Timer spawnTimer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appController = new AppController();
        appController.player = player;
        appController.spawnTimer = spawnTimer;

        Customer.allCustomers.clear();
        Customer.customersInCoffeeShop.clear();
        Customer.freeChairs.clear();
        Customer.setSpawnTimer(new Timer());
    }

    @Test
    void testStopTimers() {
        Timer customerTimer = new Timer();
        Customer customer = new Customer();
        customer.setSixtySecondsTimer(customerTimer);
        Customer.allCustomers.add(customer);

        appController.stopTimers();

        assertTrue(Customer.allCustomers.isEmpty(), "All customers should be cleared");
        assertTrue(Customer.customersInCoffeeShop.isEmpty(), "Customers in coffee shop should be cleared");
        assertTrue(Customer.freeChairs.isEmpty(), "Free chairs should be cleared");
        verify(player).setProductInHand(ProductType.NONE);
        verify(spawnTimer).cancel();
    }
}