package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GhostMonster extends Monstre{
    public GhostMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(new File("resources/images/fantome.png"));
        speed = 4;
        Random rand = new Random();
        setStep(rand.nextInt(4-1+1) + 1);

    }

    @Override
    public boolean monstreFantome() {
        return true;
    }
}
