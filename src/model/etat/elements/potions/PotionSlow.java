package model.etat.elements.potions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionSlow extends Potions {
    /**
     * Constructeur Potions
     *
     * @param p
     * @param w
     * @param h
     */
    public PotionSlow(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/potions/pootionlenteur.png"));
    }

    @Override
    public boolean isSlowPotion(){return true ;}
}
