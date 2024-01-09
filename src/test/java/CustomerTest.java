import com.decafe.game.entities.Customer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;

import static org.junit.Assert.*;

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

            assertFalse("Coin image should be invisible", customer.coinImage.isVisible());
            assertTrue("Coin image should be disabled", customer.coinImage.isDisabled());
            assertTrue("Free chairs should contain the customer's chair", Customer.freeChairs.contains(chairNumber));
            assertEquals("Free chairs list should increase by 1", initialChairCount + 1, Customer.freeChairs.size());
        });
    }

}
