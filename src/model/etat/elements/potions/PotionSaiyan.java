package model.etat.elements.potions;

import model.etat.elements.Floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionSaiyan extends Floor {
    /**
     * Constructeur Potions
     *
     * @param p
     * @param w
     * @param h
     */
    public PotionSaiyan(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/potions/potionInvisible.png"));
    }

    @Override
    public boolean isCollected() {
        return false;
    }

    @Override
    public boolean openDoor() {
        return false;
    }

    @Override
    public boolean isMagicalFloor() {
        return false;
    }

    @Override
    public boolean isActivate() {
        return false;
    }

    @Override
    public void desactivate() throws IOException {

    }

    @Override
    public boolean isSaiyanPotion(){return true ;}

}
