package model.etat.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Tresor extends Floor {

    public Tresor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        bufferedImage = ImageIO.read(new File("resources/images/tresor.png"));
    }

    @Override
    public boolean tresor() {
        return true;
    }
}
