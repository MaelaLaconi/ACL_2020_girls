package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fantome extends Monstre{
    public Fantome(Point point, int width, int height) throws IOException {
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(new File("resources/images/fantome.png"));
        speed = 4;
    }


    @Override
    public boolean monstreFantome() {
        return true;
    }
}
