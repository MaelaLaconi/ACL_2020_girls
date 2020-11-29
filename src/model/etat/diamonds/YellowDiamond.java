package model.etat.diamonds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class YellowDiamond extends Diamond {
    /**
     * Constructeur Floor
     *
     * @param p
     * @param w
     * @param h
     */

    public YellowDiamond(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/diamonds/yellow.png"));
    }



    @Override
    public boolean isBlueDiamond() {
        return false;
    }
    public boolean isRedDiamond() {
        return false;
    }
    public boolean isYellowDiamond(){
        return false;
    }


}