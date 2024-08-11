package mouse.hoi.tools.image;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Service
public class ImagesIOImpl implements ImagesIO {
    @Override
    public BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveImage(BufferedImage image, String filename) {
        String[] split = filename.split("\\.", 2);
        try {
            ImageIO.write(image, split[1], new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
