package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NormalMonster extends Monster {
    /**
     * Constructor
     * @param point
     * @param width
     * @param height
     * @throws IOException
     */
    public NormalMonster(Point point, int width, int height) throws IOException{
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(getClass().getResourceAsStream("/images/monstre.png"));
        speed = 3;
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage[indexIm], positions.x-(width/2), positions.y-(height/2),width,height,null);
    }

    @Override
    public boolean monstreNormal() {
        return true;
    }
}
