package model.etat.diamonds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Diamond
    {

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
    public Diamond(Point p, int w, int h){
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
        public void remove() throws IOException {
            bufferedImage = ImageIO.read(new File("resources/images/floor.png"));
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

    }