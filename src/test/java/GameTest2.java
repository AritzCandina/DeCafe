
    import static org.junit.jupiter.api.Assertions.assertEquals;

    import com.decafe.game.Game;
    import com.decafe.game.entities.Machine;
    import com.decafe.game.entities.Upgrade;
    import org.junit.jupiter.api.Test;

    public class GameTest2 {

        @Test
        void testGetCakeMachine() {
            // Arrange
            Game game = new Game();

            // Act
            Machine result = game.getCakeMachine();

            // Assert
            assertEquals(null, result); // Adjust this based on your actual initialization logic
        }

        @Test
        void testGetCoffeeMachine() {
            // Arrange
            Game game = new Game();

            // Act
            Machine result = game.getCoffeeMachine();

            // Assert
            assertEquals(null, result); // Adjust this based on your actual initialization logic
        }

        @Test
        void testGetCakeUpgrade() {
            // Arrange
            Game game = new Game();

            // Act
            Upgrade result = game.getCakeUpgrade();

            // Assert
            assertEquals(null, result); // Adjust this based on your actual initialization logic
        }

        @Test
        void testGetCoffeeUpgrade() {
            // Arrange
            Game game = new Game();

            // Act
            Upgrade result = game.getCoffeeUpgrade();

            // Assert
            assertEquals(null, result); // Adjust this based on your actual initialization logic
        }

        @Test
        void testGetPlayerUpgrade() {
            // Arrange
            Game game = new Game();

            // Act
            Upgrade result = game.getPlayerUpgrade();

            // Assert
            assertEquals(null, result); // Adjust this based on your actual initialization logic
        }

        @Test
        void testGetCoinsEarned() {
            // Arrange
            Game game = new Game();

            // Act
            int result = game.getCoinsEarned();

            // Assert
            assertEquals(0, result); // Adjust this based on your actual initialization logic
        }
    }


