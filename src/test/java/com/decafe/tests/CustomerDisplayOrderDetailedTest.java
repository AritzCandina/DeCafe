package com.decafe.tests;


import com.decafe.game.entities.Customer;
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

import static org.mockito.Mockito.*;

public class CustomerDisplayOrderDetailedTest {

    private static class TestableCustomer extends Customer {
        private ProductType forcedOrder;

        public TestableCustomer(ImageView image, ImageView label, int chair, ImageView smiley, ImageView coinImage, ProductType forcedOrder) {
            super(image, label, chair, smiley, coinImage);
            this.forcedOrder = forcedOrder;
        }

        @Override
        public ProductType getRandomOrder() {
            return forcedOrder;
        }
    }


    @Mock
    private ImageView orderLabel;

    private TestableCustomer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void testDisplayOrderForProductTypeAndChair(ProductType productType, int chair, String expectedImagePath) throws FileNotFoundException {
        customer.setChair(chair);
        customer.setOrder(productType);
        Image image = ResourceProvider.createImage(expectedImagePath);
        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(expectedImagePath)).thenReturn(image);

            customer.displayOrder(orderLabel);

            verify(orderLabel).setVisible(true);
            verify(orderLabel).setImage(image);
        }
    }

    @Test
    void testDisplayOrderWithCakeTopLeft() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.CAKE);
        testDisplayOrderForProductTypeAndChair(ProductType.CAKE, 0, ImageFiles.BUBBLE_CAKE_TOP_LEFT);
    }

    @Test
    void testDisplayOrderWithCakeTopRight() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.CAKE);
        testDisplayOrderForProductTypeAndChair(ProductType.CAKE, 2, ImageFiles.BUBBLE_CAKE_TOP_RIGHT);
    }

    @Test
    void testDisplayOrderWithCakeBottomRight() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.CAKE);
        testDisplayOrderForProductTypeAndChair(ProductType.CAKE, 5, ImageFiles.BUBBLE_CAKE_BOTTOM_RIGHT);
    }

    @Test
    void testDisplayOrderWithCoffeeTopLeft() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.COFFEE);
        testDisplayOrderForProductTypeAndChair(ProductType.COFFEE, 0, ImageFiles.BUBBLE_COFFEE_TOP_LEFT);
    }

    @Test
    void testDisplayOrderWithCoffeeTopRight() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.COFFEE);
        testDisplayOrderForProductTypeAndChair(ProductType.COFFEE, 2, ImageFiles.BUBBLE_COFFEE_TOP_RIGHT);
    }

    @Test
    void testDisplayOrderWithCoffeeBottomRight() throws FileNotFoundException {
        customer = new TestableCustomer(null, orderLabel, 0, null, null, ProductType.COFFEE);
        testDisplayOrderForProductTypeAndChair(ProductType.COFFEE, 5, ImageFiles.BUBBLE_COFFEE_BOTTOM_RIGHT);
    }
}
