package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalMonstre extends Monstre {

    public NormalMonstre(Point point, int width, int height) throws IOException{
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(new File("resources/images/monstre.png"));
        speed = 3;
    }

    @Override
    public boolean monstreNormal() {
        return true;
    }
}
