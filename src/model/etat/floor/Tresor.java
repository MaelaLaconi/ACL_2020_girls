package model.etat.floor;

import model.etat.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Tresor extends Floor {
    private boolean isCollected;

    public Tresor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/tresor.png"));
        isCollected = false;
    }


    /**
     * Function that will activate power ( à revoir)
     * @param hero
     */
    public void collected(Hero hero) throws IOException {
        isCollected = true;
        emptyTresor();

    }

    /**
     *Function once the hero is on the magicstep
     */
    private void emptyTresor() throws IOException {
        bufferedImage = ImageIO.read(new File("resources/images/tresorOpen.png"));
    }



    @Override
    public boolean isCollected() {
        return isCollected;
    }
    public boolean isTresor() {
        return true;
    }
    public boolean openDoor() {
        return false;
    }
}
