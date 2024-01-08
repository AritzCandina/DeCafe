package uselessTests;

public class GlideImageLoader implements ImageLoader {
    @Override
    public void loadImage(String path, Object target) {
        // Simulate loading image with Glide
        System.out.println("Loading image from path: " + path);
        // In a real application, you'd use an image loading library like Glide.
    }
}
