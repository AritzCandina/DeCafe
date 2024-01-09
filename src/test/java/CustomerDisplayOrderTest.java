import com.decafe.game.entities.Customer;
import com.decafe.game.entities.ProductType;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class CustomerDisplayOrderTest {

    private ImageView orderLabel;

    @Spy
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = spy(new Customer());
        orderLabel = new ImageView();
    }

    @Test
    public void testDisplayOrderForCakeTopLeft() throws FileNotFoundException {
        doReturn(ProductType.CAKE).when(customer).getRandomOrder();

        try (MockedStatic<ResourceProvider> mocked = Mockito.mockStatic(ResourceProvider.class)) {
            mocked.when(() -> ResourceProvider.createImage(ImageFiles.BUBBLE_CAKE_TOP_LEFT))
                    .thenReturn(new Image("file:top_left_cake.png"));
        }

        customer.setChair(0);
        customer.displayOrder(orderLabel);

        assertTrue(orderLabel.isVisible());
        assertNotNull(orderLabel.getImage());
        verify(customer).getRandomOrder();
    }

    @Test
    public void testDisplayOrderForCoffeeTopLeft() throws FileNotFoundException {
        doReturn(ProductType.COFFEE).when(customer).getRandomOrder();
        try (MockedStatic<ResourceProvider> mocked = Mockito.mockStatic(ResourceProvider.class)) {
            mocked.when(() -> ResourceProvider.createImage(ImageFiles.BUBBLE_COFFEE_TOP_LEFT))
                    .thenReturn(new Image("file:top_left_coffee.png"));
        }

        customer.setChair(0);
        customer.displayOrder(orderLabel);

        assertTrue(orderLabel.isVisible());
        assertNotNull(orderLabel.getImage());
        verify(customer).getRandomOrder();
    }


}