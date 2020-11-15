package model.etat.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Wall extends Floor {

    public Wall(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/wall.png"));
    }

    @Override
    public boolean isCollected() {
        return false;
    }
    public boolean openDoor() {
        return false;
    }
    public boolean isMagicalFloor() {
        return false;
    }

    public boolean isActivate() {
        return false;
    }
    public void desactivate() {

    }
}
