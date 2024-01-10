package com.decafe.tests;

import com.decafe.game.Game;
import com.decafe.game.entities.Customer;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @BeforeEach
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
        assertNotNull( game.getCoffeeMachine());
        assertNotNull( game.getCakeMachine());
        assertNotNull(game.getCoffeeUpgrade());
        assertNotNull(game.getCakeUpgrade());
        assertNotNull(game.getPlayerUpgrade());
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
