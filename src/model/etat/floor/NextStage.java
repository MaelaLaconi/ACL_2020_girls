package model.etat.floor;


import model.etat.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NextStage extends Floor {
    private boolean isOpen;

    public NextStage(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/doorclosed.png"));
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
    public boolean isAtDoor() {
        return true;
    }
}

