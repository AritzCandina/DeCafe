import com.decafe.game.entities.Customer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class CustomerOrderLabelTest extends ApplicationTest {

    private ImageView[] customerImages;
    private ImageView[] orderLabels;

    @Override
    public void start(Stage stage) {
        customerImages = new ImageView[3];
        orderLabels = new ImageView[3];

        for (int i = 0; i < customerImages.length; i++) {
            customerImages[i] = new ImageView(new Image("file:customer" + i + ".png"));
            orderLabels[i] = new ImageView(new Image("file:orderLabel" + i + ".png"));
        }

        Customer.customerImages = customerImages;
        Customer.orderLabels = orderLabels;
    }

    @Test
    public void testGetCorrectCustomerOrderLabel_MatchFound() {
        interact(() -> {
            ImageView result = Customer.getCorrectCustomerOrderLabel(customerImages[1]);

            assertEquals("Should return the corresponding order label ImageView from orderLabels", orderLabels[1], result);
        });
    }

    @Test
    public void testGetCorrectCustomerOrderLabel_NoMatchFound() {
        interact(() -> {
            ImageView newCustomerImage = new ImageView(new Image("file:newCustomer.png"));

            ImageView result = Customer.getCorrectCustomerOrderLabel(newCustomerImage);

            assertNull("Should return null when no match is found", result);
        });
    }

}
