package model.etat.floor;

import java.awt.*;
import java.io.IOException;
import java.net.URL;


public class TeleportStep extends Floor {

    public TeleportStep(Point p, int w, int h) throws IOException {
        super(p, w, h);
        URL url = new URL("https://f.hellowork.com/blogdumoderateur/2013/02/gif-anime.gif");
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
