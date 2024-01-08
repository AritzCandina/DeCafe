package uselessTests;

public class ImageUser {
    private final ImageLoader imageLoader;

    public ImageUser(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void useImage(String path, Object target) {
        // Some logic here
        imageLoader.loadImage(path, target);
        // Other actions related to the image
    }
}
