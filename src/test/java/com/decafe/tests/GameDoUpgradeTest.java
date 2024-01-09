package com.decafe.tests;

import com.decafe.game.Game;
import com.decafe.game.entities.Machine;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.Upgrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameDoUpgradeTest {

    @Mock
    private Machine coffeeMachine;
    @Mock
    private Machine cakeMachine;
    @Mock
    private Upgrade coffeeUpgrade;
    @Mock
    private Upgrade cakeUpgrade;
    @Mock
    private Upgrade playerUpgrade;
    @Mock
    private Player cofiBrew;

    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        game = new Game(); // Initialize Game without parameters for simplicity
        game.coffeeMachine = coffeeMachine;
        game.cakeMachine = cakeMachine;
        game.coffeeUpgrade = coffeeUpgrade;
        game.cakeUpgrade = cakeUpgrade;
        game.playerUpgrade = playerUpgrade;
    }

    @Test
    public void testDoUpgradeCoffee() throws FileNotFoundException {
        int initialCoins = 100;
        when(coffeeUpgrade.doUpgrade(initialCoins)).thenReturn(120);
        game.coinsEarned = initialCoins;

        game.doUpgrade(Game.UPGRADE_TYPE_COFFEE, cofiBrew);

        assertEquals(120, game.getCoinsEarned(), "Coins earned should be updated after coffee upgrade");
        verify(coffeeMachine).setProductionDuration(Game.UPGRADED_MACHINE_DURATION);
    }

    @Test
    public void testDoUpgradeCake() throws FileNotFoundException {
        int initialCoins = 100;
        int expectedCoinsAfterUpgrade = 130;
        when(cakeUpgrade.doUpgrade(initialCoins)).thenReturn(expectedCoinsAfterUpgrade);
        game.coinsEarned = initialCoins;

        game.doUpgrade(Game.UPGRADE_TYPE_CAKE, cofiBrew);

        assertEquals(expectedCoinsAfterUpgrade, game.getCoinsEarned(), "Coins earned should be updated after cake upgrade");
        verify(cakeMachine).setProductionDuration(Game.UPGRADED_MACHINE_DURATION);
    }

    @Test
    public void testDoUpgradePlayer() throws FileNotFoundException {
        int initialCoins = 100;
        int expectedCoinsAfterUpgrade = 140;
        when(playerUpgrade.doUpgrade(initialCoins)).thenReturn(expectedCoinsAfterUpgrade);
        game.coinsEarned = initialCoins;

        game.doUpgrade(Game.UPGRADE_TYPE_PLAYER, cofiBrew);

        assertEquals(expectedCoinsAfterUpgrade, game.getCoinsEarned(), "Coins earned should be updated after player upgrade");
        verify(cofiBrew).setMovement(Game.UPGRADED_MOVEMENT_SPEED);
    }
}
