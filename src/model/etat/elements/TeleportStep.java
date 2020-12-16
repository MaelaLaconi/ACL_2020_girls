package model.etat.elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class TeleportStep extends Floor {
    private boolean stepIn;
    private int x;
    private  int y ;

    /**
     * Constructor
     * @param p point
     * @param w largeur
     * @param h hauteur
     * @throws IOException
     */
    public TeleportStep(Point p, int w, int h) throws IOException {
        super(p, w, h);
        x= (int) p.getX();
        y= (int) p.getY();
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/teleport.png"));
        stepIn=false;
    }

    public boolean isStepIn() {
        return stepIn;
    }

    public void setStepIn(boolean stepIn) {
        this.stepIn = stepIn;
    }

    @Override
    public boolean isTeleportStep(){
        return true;
    }
    public boolean isCollected() {
        return false;
    }

    public boolean openDoor() {
        return false;
    }

    public boolean isMagicalFloor() {
        return false;
    }

    public boolean isActivate() {
        return false;
    }

    public void desactivate(){
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
