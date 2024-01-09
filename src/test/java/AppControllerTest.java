import com.decafe.AppController;
import com.decafe.game.Game;
import com.decafe.game.entities.Player;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
public class AppControllerTest extends ApplicationTest {

    private AppController appController;
    private Game game;
    private Player player;
    private Label coinsEarnedLabel;


    @Override
    public void start(Stage stage) {
        game = Mockito.mock(Game.class);
        player = Mockito.mock(Player.class);
        coinsEarnedLabel = Mockito.mock(Label.class);
        ImageView playerUpgradeImageView = Mockito.mock(ImageView.class);
        ImageView waiterImageView = Mockito.mock(ImageView.class);

        appController = new AppController();
        appController.game = game;
        appController.player = player;
        appController.coinsEarnedLabel = coinsEarnedLabel;
        appController.waiterImageView = waiterImageView;
        appController.upgradePlayerImageView = playerUpgradeImageView;
    }


    @Test
    public void testDoUpgrade() throws FileNotFoundException {
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);
        ImageView sourceImageView = Mockito.mock(ImageView.class);
        when(mockEvent.getSource()).thenReturn(sourceImageView);
        when(sourceImageView.getId()).thenReturn("someUpgradeId");
        when(game.getCoinsEarned()).thenReturn(50);

        appController.doUpgrade(mockEvent);

        Mockito.verify(game).doUpgrade("someUpgradeId", player);
        Mockito.verify(coinsEarnedLabel).setText("50");
        Mockito.verify(game).checkUpgradePossible(game.getCoffeeUpgrade());
        Mockito.verify(game).checkUpgradePossible(game.getCakeUpgrade());
        Mockito.verify(game).checkUpgradePossible(game.getPlayerUpgrade());
    }


    @Test
    public void testKeyPressed() {
        KeyEvent mockEvent = Mockito.mock(KeyEvent.class);
        when(mockEvent.getCode()).thenReturn(KeyCode.W);

        appController.keyPressed(mockEvent);

        assertTrue(appController.wPressed.get());
    }

    @Test
    public void testKeyReleased() {
        KeyEvent mockEvent = Mockito.mock(KeyEvent.class);
        when(mockEvent.getCode()).thenReturn(KeyCode.W);

        appController.wPressed.set(true);
        appController.keyReleased(mockEvent);

        assertFalse(appController.wPressed.get());
    }


}
