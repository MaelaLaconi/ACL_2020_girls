package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GhostMonster extends Monster {
    public GhostMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(getClass().getResourceAsStream("/images/fantome.png"));
        speed = 4;
        Random rand = new Random();
        setStep(rand.nextInt(4-1+1) + 1);
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage[indexIm], positions.x-(width/2), positions.y-(height/2),width,height,null);
    }

    @Override
    public boolean monstreFantome() {
        return true;
    }
}
