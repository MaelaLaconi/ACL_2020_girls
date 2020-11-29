package model.etat.diamonds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Diamond {

        protected Point position;
        protected int width;
        protected int height;
        protected BufferedImage bufferedImage;
        private boolean isPicked;


        /**
         * Constructeur Floor
         * @param p
         * @param w
         * @param h
         */
    public Diamond(Point p, int w, int h){
        position = p;
        width = w;
        height = h;
        isPicked=false;

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

    /**
     * replace the diamond by floor
     * @throws IOException
     */
    public void remove() throws IOException {
        bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/floor.png"));
    }

    /**
     * when the hero has picked the diamond it's removed
     *
     */
    public void picked() throws IOException {
        isPicked = true;
        remove();
    }

    public boolean isPicked() {
        return isPicked;
    }


    public Point getPosition() {
        return position;
    }

    public boolean isBlueDiamond() {
        return false;
    }

    public boolean isRedDiamond() {
        return false;
    }
    public boolean isYellowDiamond(){
        return false;
    }

    }