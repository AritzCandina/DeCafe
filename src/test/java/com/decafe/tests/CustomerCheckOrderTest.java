package com.decafe.tests;

import com.decafe.game.entities.Customer;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.ProductType;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CustomerCheckOrderTest {

    @Mock
    private Player cofiBrew;
    @Mock
    private ImageView customerImage, orderLabel, smiley, coinImage, waiterImage;
    @Mock
    private Image mockImage;


    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(customerImage, orderLabel, 0, smiley, coinImage);
        Customer.setSpawnTimer(new Timer());
        customer.setSixtySecondsTimer(new Timer());
    }

    @Test
    void testCheckOrderCorrect() throws FileNotFoundException {
        when(cofiBrew.getProductInHand()).thenReturn(ProductType.COFFEE);
        customer.setOrder(ProductType.COFFEE);
        when(cofiBrew.getFilenameImageWithoutProduct()).thenReturn(ImageFiles.COFI_BREW_UP);

        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(ImageFiles.COFI_BREW_UP)).thenReturn(mockImage);

            boolean result = customer.checkOrder(cofiBrew, customer, waiterImage);

            assertTrue(result, "Order should be correct");
            verify(cofiBrew).setProductInHand(ProductType.NONE);
            verify(orderLabel).setVisible(false);
        }
    }

    @Test
    void testCheckOrderIncorrect() throws FileNotFoundException {
        when(cofiBrew.getProductInHand()).thenReturn(ProductType.CAKE);
        customer.setOrder(ProductType.COFFEE);
        when(cofiBrew.getFilenameImageWithoutProduct()).thenReturn(ImageFiles.COFI_BREW_UP);

        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(ImageFiles.COFI_BREW_UP)).thenReturn(mockImage);

            boolean result = customer.checkOrder(cofiBrew, customer, waiterImage);
            assertFalse(result, "Order should be incorrect");
            verify(cofiBrew).setProductInHand(ProductType.NONE);
            verify(orderLabel).setVisible(false);
        }
    }
}