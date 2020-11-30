package model.etat.elements.potions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionSaiyan extends Potions{
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
    public boolean isSaiyanPotion(){return true ;}

}
