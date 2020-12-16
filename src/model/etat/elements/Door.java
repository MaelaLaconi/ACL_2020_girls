package model.etat.elements;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends Floor {
    private boolean isOpen;

    /**
     * Constructor
     * @param p
     * @param w
     * @param h
     * @throws IOException
     */
    public Door(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/doorclosed.png"));
        isOpen = false;
    }

    /**
     * fixe open (true ou false)
     * @param open
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public boolean isCollected(){
        return false;
    }

    /**
     *
     * @return si la porte est ouverte ou non
     */
    public boolean openDoor() {
        return isOpen;
    }

    /**
     *
     * @return si le floor est magique
     */
    public boolean isMagicalFloor() {
        return false;
    }

    public boolean isActivate() {
        return false;
    }

    public void desactivate() {

    }

    public boolean isAtDoor() {
        return true;
    }
}

