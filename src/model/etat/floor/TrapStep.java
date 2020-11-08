package model.etat.floor;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TrapStep extends Floor {
    private boolean isActivate;

    /**
     * Constructeur Floor
     *
     * @param p
     * @param w
     * @param h
     */
    public TrapStep(Point p, int w, int h) {
        super(p, w, h);
        try {
            bufferedImage = ImageIO.read(new File("resources/images/trapStep.png"));
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
        bufferedImage = ImageIO.read(new File("resources/images/trapStepIn.png"));
    }

    public void activate() throws IOException {
        isActivate = true;
        desactivate();
    }
}
