package com.decafe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNull;

    import com.decafe.game.Game;
    import com.decafe.game.entities.Machine;
    import com.decafe.game.entities.Upgrade;
    import org.junit.jupiter.api.Test;

    public class GameGetterTests {

        @Test
        void testGetCakeMachine() {
            Game game = new Game();

            Machine result = game.getCakeMachine();

            assertNull(result);
        }

        @Test
        void testGetCoffeeMachine() {
            Game game = new Game();

            Machine result = game.getCoffeeMachine();

            assertNull(result);
        }

        @Test
        void testGetCakeUpgrade() {
            Game game = new Game();

            Upgrade result = game.getCakeUpgrade();

            assertEquals(null, result);
        }

        @Test
        void testGetCoffeeUpgrade() {
            Game game = new Game();

            Upgrade result = game.getCoffeeUpgrade();

            assertEquals(null, result);
        }

        @Test
        void testGetPlayerUpgrade() {
            Game game = new Game();

            Upgrade result = game.getPlayerUpgrade();

            assertEquals(null, result);
        }

        @Test
        void testGetCoinsEarned() {
            Game game = new Game();

            int result = game.getCoinsEarned();

            assertEquals(0, result);
        }
    }


