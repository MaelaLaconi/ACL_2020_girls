package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalMonstre extends Monstre {

    public NormalMonstre(Point point, int width, int height) throws IOException{
        super(point, width, height);
        bufferedImage = ImageIO.read(new File("resources/images/monstre.png"));
        speed = 3;

    }

    @Override
    public void draw(BufferedImage im) throws IOException {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage, positions.x-(width/2), positions.y-(height/2),width,height,null);
    }


}
