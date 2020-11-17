package model.etat.diamonds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BlueDiamond extends Diamond {
    /**
     * Constructeur Floor
     *
     * @param p
     * @param w
     * @param h
     */

    public BlueDiamond(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/bleu.png"));
    }



    @Override
    public boolean isBlueDiamond() {
        return false;
    }
    public boolean isRedDiamond() {
        return false;
    }


}