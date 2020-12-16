package model.etat.elements;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TrapStep extends Floor {
    private boolean isActivate;

    /**
     * Constructeur Floor
     *
     * @param p point
     * @param w largeur
     * @param h hauteur
     */
    public TrapStep(Point p, int w, int h) {
        super(p, w, h);
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/trapStep.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        isActivate = false;
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
    public boolean isTrapStep() {
        return true;
    }

    @Override
    public boolean isActivate() {
        return isActivate;
    }

    @Override
    public void desactivate() throws IOException {
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/trapStepIn.png"));
    }

    public void activate() throws IOException {
        isActivate = true;
        desactivate();
    }
}
