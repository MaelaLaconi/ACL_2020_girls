package model.etat.floor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract class Floor
 */
public abstract class Floor {

    protected Point position;
    protected int width;
    protected int height;
    protected BufferedImage bufferedImage;

    /**
     * Constructeur Floor
     * @param p
     * @param w
     * @param h
     */
    public Floor(Point p, int w, int h){
        position = p;
        width = w;
        height = h;
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    public void draw(BufferedImage im) throws IOException{
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage,position.x,position.y,width,height,null);
    }

    public Point getPosition() {
        return position;
    }

    public boolean tresor(){
        return false;
    }

    public boolean isMagicalFloor() {
        return false;
    }
}
