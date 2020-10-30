package model.etat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero {

    private Point position;
    private int width;
    private int height;
    private BufferedImage im;

    public Hero() throws IOException {
        position = new Point(0,0);
        width = 30;
        height = 30;
        im = ImageIO.read(new File("resources/images/hero.png"));
    }

    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x-(width/2),position.y-(height/2),width,height,null);
    }

    public void move(int x, int y){
        position.x += x;
        position.y += y;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
