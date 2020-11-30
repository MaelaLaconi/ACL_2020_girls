package model.etat.elements.potions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PotionWall extends Potions {
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
    public boolean isWallPotion(){return true ;}

}
