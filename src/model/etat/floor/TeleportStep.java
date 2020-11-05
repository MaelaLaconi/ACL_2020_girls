package model.etat.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class TeleportStep extends Floor {

    public TeleportStep(Point p, int w, int h) throws IOException {
        super(p, w, h);
        /*)*/
        bufferedImage = ImageIO.read(new File("resources/images/teleport.png"));
       // bufferedImage = new ImageInput(url);
    }

    @Override
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
}
