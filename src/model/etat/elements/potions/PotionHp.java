package model.etat.elements.potions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionHp extends Potions{
    /**
     * Constructeur Potions
     *
     * @param p
     * @param w
     * @param h
     */
    public PotionHp(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/potions/potionHp.png"));
    }

    @Override
    public boolean isHpPotion(){return true ;}

}
