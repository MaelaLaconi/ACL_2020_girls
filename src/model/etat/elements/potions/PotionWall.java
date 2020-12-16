package model.etat.elements.potions;

import model.etat.elements.Floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionWall extends Floor {
    /**
     * Constructeur Potions
     *
     * @param p
     * @param w
     * @param h
     */
    public PotionWall(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/potions/potionMur.png"));
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
    public boolean isWallPotion(){return true ;}

}
