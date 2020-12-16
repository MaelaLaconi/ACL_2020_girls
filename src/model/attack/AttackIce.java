package model.attack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AttackIce extends Attack {
    /**
     * constructor
     * @param point
     * @param width
     * @param height
     * @throws IOException
     */
    public AttackIce(Point point, int width, int height) throws IOException {
        super(point, width, height);
        nbFrame=1;
        bufferedImage[0] = ImageIO.read(getClass().getResourceAsStream("/images/attack.png"));
        speed = 3;
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage[indexIm], positions.x-(width/2), positions.y-(height/2),width,height,null);
    }


}
