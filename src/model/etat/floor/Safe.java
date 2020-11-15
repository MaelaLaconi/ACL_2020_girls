package model.etat.floor;

import model.etat.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Safe extends Floor {
    private boolean isCollected;

    public Safe(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/tresor.png"));
        isCollected = false;
    }


    /**
     * Function that indicate that the safe has been collected
     *
     */
    public void collected() throws IOException {
        isCollected = true;
        emptyTresor();
    }

    /**
     *Function once the hero is on the safe
     */
    private void emptyTresor() throws IOException {
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/tresorOpen.png"));
    }



    @Override
    public boolean isCollected() {
        return isCollected;
    }
    public boolean isSafe() {
        return true;
    }
    public boolean openDoor() {
        return false;
    }

    @Override
    public boolean isMagicalFloor() {
        return false;
    }
    public boolean isActivate() {
        return false;
    }
    public void desactivate() {
    }
}
