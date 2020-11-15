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

    public boolean isMagicalStep() {
        return false;
    }

    public abstract boolean isCollected();

    public boolean isSafe(){
        return false;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public boolean isAtDoor(){
        return false;
    }

    public abstract boolean openDoor();

    public abstract boolean isMagicalFloor();

    public boolean isNormalStep(){ return false;}

    public abstract boolean isActivate();

    public boolean isTrapStep(){ return false ;}

    public abstract void desactivate() throws IOException;

    public boolean isTeleportStep(){
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
