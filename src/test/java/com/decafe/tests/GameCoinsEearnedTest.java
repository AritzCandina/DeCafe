package com.decafe.tests;

import com.decafe.game.Game;
import com.decafe.game.entities.Customer;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(ApplicationExtension.class)
public class GameCoinsEearnedTest {

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
        ImageView dummyImageView = new ImageView();
        game = new Game(dummyImageView, dummyImageView, dummyImageView);
        when(mockGreenCustomer.isGreen()).thenReturn(true);
        when(mockYellowCustomer.isYellow()).thenReturn(true);
        when(mockRedCustomer.isRed()).thenReturn(true);
    }

    @Test
    public void testConstructorWithImageViews() {
        assertNotNull("Coffee machine should not be null", game.getCoffeeMachine());
        assertNotNull("Cake machine should not be null", game.getCakeMachine());
        assertNotNull("Coffee upgrade should not be null", game.getCoffeeUpgrade());
        assertNotNull("Cake upgrade should not be null", game.getCakeUpgrade());
        assertNotNull("Player upgrade should not be null", game.getPlayerUpgrade());
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
