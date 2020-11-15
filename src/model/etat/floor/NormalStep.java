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
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/floor.png"));
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

    @Override
    public boolean isNormalStep() {
        return true ;
    }

    public boolean isActivate() {
        return false;
    }

    public void desactivate() {

    }
}
