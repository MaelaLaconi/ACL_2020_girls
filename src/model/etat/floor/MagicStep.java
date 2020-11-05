package model.etat.floor;

import model.etat.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Class MagicFloor
 */
public class MagicStep extends Floor{
    private boolean isActivate;

    public MagicStep(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/magic.png"));
        isActivate = false;
    }


    /**
     * Function that will activate power ( à revoir)
     * @param hero
     */
    public void activate(Hero hero) throws IOException {
        isActivate = true;
        desactivate();
    }

    /**
     *Function once the hero is on the magicstep
     */
    public void desactivate() throws IOException {
        bufferedImage = ImageIO.read(new File("resources/images/magicStepIn.png"));
    }


    @Override
    public boolean isMagicalStep() {
        return true;
    }
    public boolean isCollected() {
        return false;
    }
    public boolean openDoor() {return false;}

    public boolean isMagicalFloor() {
        return false;
    }

    public boolean isActivate() {
        return isActivate;
    }
}

