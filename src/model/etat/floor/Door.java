package model.etat.floor;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Door extends Floor {
    private boolean isOpen;

    public Door(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/doorclosed.png"));
        isOpen = false;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public boolean isCollected(){
        return false;
    }

    public boolean openDoor() {
        return isOpen;
    }

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

