package model.etat.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class NormalStep extends Floor {

    public NormalStep(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/floor.png"));
    }

    @Override
    public boolean isCollected() {
        return false;
    }
    public boolean openDoor() {
        return false;
    }
}