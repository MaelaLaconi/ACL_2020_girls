package model.etat.diamonds;

import model.etat.Hero;
import model.etat.diamonds.Diamond;
import model.etat.floor.Floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DiamondBleu extends Diamond {
    /**
     * Constructeur Floor
     *
     * @param p
     * @param w
     * @param h
     */
    private boolean isPicked;
    public DiamondBleu(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/bleu.png"));
        isPicked=false;
    }

    /**
     * Function that will activate power ( à revoir)
     * @param hero
     */
    public void picked(Hero hero) throws IOException {
        isPicked = true;
        remove();
    }

    public boolean isPicked() {
        return isPicked;
    }
    @Override
    public boolean isBlueDiamond() {
        return false;
    }
    public boolean isRedDiamond() {
        return false;
    }


}