import com.decafe.game.entities.Customer;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.Assert.*;

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

        assertEquals("Image should be correctly set", mockImage, customer.getCorrectCustomerImage());
        assertEquals("Label should be correctly set", mockLabel, customer.getCorrectCustomerOrderLabel());
        assertEquals("Chair number should be correctly set", chairNumber, customer.getChair());
        assertEquals("Coin image should be correctly set", mockCoinImage, customer.getCoinImage());
        assertFalse("alreadyOrdered should be initialized to false", customer.isAlreadyOrdered());
        assertNotNull("Timer should be initialized", customer.getSixtySecondsTimer());
    }

}
