package com.decafe.tests;

import com.decafe.game.Game;
import com.decafe.game.entities.Upgrade;
import com.decafe.resources.ResourceProvider;
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

class GameTest {

    @Mock
    private Upgrade upgrade;
    @Mock
    private ImageView upgradeImageView;
    @Mock
    private Image mockImage;

    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game();
        when(upgrade.getUpgradeImageView()).thenReturn(upgradeImageView);
    }

    @Test
    void testCheckUpgradePossible() throws FileNotFoundException {
        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(anyString())).thenReturn(mockImage);

            when(upgrade.isAlreadyUsedOnce()).thenReturn(false);
            when(upgrade.getCoinsNeeded()).thenReturn(10);
            game.coinsEarned = 15; // Enough coins

            game.checkUpgradePossible(upgrade);

            verify(upgradeImageView).setDisable(false);
        }
    }


    @Test
    void testCheckUpgradeNotPossible() throws FileNotFoundException {
        try (MockedStatic<ResourceProvider> mockedResourceProvider = Mockito.mockStatic(ResourceProvider.class)) {
            mockedResourceProvider.when(() -> ResourceProvider.createImage(anyString())).thenReturn(mockImage);

            when(upgrade.isAlreadyUsedOnce()).thenReturn(false);
            when(upgrade.getCoinsNeeded()).thenReturn(10);
            game.coinsEarned = 5; // Not enough coins

            game.checkUpgradePossible(upgrade);

            verify(upgradeImageView).setDisable(true);
        }
    }
}
