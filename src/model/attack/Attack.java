package model.attack;

import model.etat.Lab.Labyrinthe;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Attack { public static int HAUT = 4 ;
    public static int BAS = 2 ;
    public static int GAUCHE = 3 ;
    public static int DROITE = 1 ;


    protected int width;
    protected int height;
    protected Point positions;
    protected int speed;
    protected ArrayList<Integer> listedepoints;
    public BufferedImage[] getBufferedImage() {
        return bufferedImage;
    }

    protected BufferedImage[] bufferedImage;
    protected int indexIm,nbFrame;
    private boolean destryo;
    private int step ;

    public Attack(Point point, int width, int height){
        this.positions = point;
        this.width = width;
        this.height = height;
        bufferedImage=new BufferedImage[100];
        indexIm=0;
        step = 1 ;
        listedepoints =new ArrayList<>();
        listedepoints.add(point.x);
        destryo = false;
    }


    /**
     *  move normal monster (collisions with walls)
     * @param labyrinthe
     * @param wallWidth
     * @param wallHeight
     */

    public void move(Labyrinthe labyrinthe, int wallWidth, int wallHeight){
       if(step == DROITE) {
            if(!labyrinthe.isWall(positions.x + speed + wallWidth/2, positions.y)){
                positions.x += speed;
            }
            else {
               destryo = true;
            }
        }

        else if(step == GAUCHE) {
            if(!labyrinthe.isWall(positions.x - speed - wallWidth/2, positions.y)){
                positions.x -= speed;
            }
            else {
                destryo = true;
            }
        }

        else if(step == HAUT) {
            if(!labyrinthe.isWall(positions.x, positions.y - speed - wallHeight/2)){
                positions.y -= speed;
            }
            else {
                destryo = true;
            }
        }

        else if(step == BAS) {
            if(!labyrinthe.isWall(positions.x, positions.y + speed + wallHeight/2)){
                positions.y += speed;
            }
            else {
                destryo = true;
            }
        }
    }



    /**
     * draw the attack on the screen
     *
     * @param im
     */
    public abstract void draw(BufferedImage im) ;


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDestryo() {
        return destryo;
    }
}