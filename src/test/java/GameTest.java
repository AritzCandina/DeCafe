import com.decafe.game.Game;
import com.decafe.game.entities.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class GameTest {

    private Game game;

    @Mock
    private Customer mockGreenCustomer;
    @Mock
    private Customer mockYellowCustomer;
    @Mock
    private Customer mockRedCustomer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        game = new Game();

        when(mockGreenCustomer.isGreen()).thenReturn(true);
        when(mockYellowCustomer.isYellow()).thenReturn(true);
        when(mockRedCustomer.isRed()).thenReturn(true);
    }

    @Test
    public void testSetCoinsEarnedGreen() {
        int initialCoins = game.getCoinsEarned();
        game.setCoinsEarned(mockGreenCustomer);
        assertEquals(initialCoins + Game.COIN_REWARD_GREEN, game.getCoinsEarned());
    }

    @Test
    public void testSetCoinsEarnedYellow() {
        int initialCoins = game.getCoinsEarned();
        game.setCoinsEarned(mockYellowCustomer);
        assertEquals(initialCoins + Game.COIN_REWARD_YELLOW, game.getCoinsEarned());
    }

    @Test
    public void testSetCoinsEarnedRed() {
        int initialCoins = game.getCoinsEarned();
        game.setCoinsEarned(mockRedCustomer);
        assertEquals(initialCoins + Game.COIN_REWARD_RED, game.getCoinsEarned());
    }




}
