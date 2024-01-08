package uselessTests;

// Test class
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ImageUserTest {

    @Test
    public void testUseImage() {
        // Arrange
        ImageLoader mockedImageLoader = Mockito.mock(ImageLoader.class);
        ImageUser imageUser = new ImageUser(mockedImageLoader);
        String imagePath = "https://example.com/image.jpg";
        Object target = new Object();

        // Act
        imageUser.useImage(imagePath, target);

        // Assert
        verify(mockedImageLoader, times(1)).loadImage(eq(imagePath), eq(target));
    }
}