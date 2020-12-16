package model.etat.elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 *
 */
public class Wall extends Floor {
    /**
     *
     * @param p point
     * @param w largeur
     * @param h hauteur
     * @throws IOException
     */
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
