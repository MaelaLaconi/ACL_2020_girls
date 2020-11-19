package model.etat.elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Potions  extends Floor{
    private boolean isCollected;

    /**
     * Constructeur Potions
     *
     * @param p
     * @param w
     * @param h
     */
    public Potions(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/potionHp.png"));
        isCollected = false;
    }


    /**
     * Function that will add hp to the hero
     *
     */
    public void drinkPotion() throws IOException {
        isCollected = true;
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/floor.png"));


    }



    @Override
    public boolean isPotion(){ return true;}
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
    public void desactivate() throws IOException {

    }
}
