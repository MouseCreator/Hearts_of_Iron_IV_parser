package mouse.hoi.tools.image;

import java.awt.image.BufferedImage;

public interface ImagesIO {
    BufferedImage loadImage(String filename);
    void saveImage(BufferedImage image, String filename);
}
