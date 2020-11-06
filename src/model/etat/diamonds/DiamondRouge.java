package model.etat.diamonds;

import model.etat.Hero;
import model.etat.diamonds.Diamond;
import model.etat.floor.Floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DiamondRouge extends Diamond {
    /**
     * Constructeur Floor
     *
     * @param p
     * @param w
     * @param h
     */
    private boolean isPicked;
    public DiamondRouge(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/rouge.png"));
        isPicked=false;
    }

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
