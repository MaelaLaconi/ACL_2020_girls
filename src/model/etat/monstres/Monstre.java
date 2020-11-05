package model.etat.monstres;

import model.etat.Labyrinthe;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 *
 */

public abstract class Monstre {
    public static int HAUT = 4 ;
    public static int BAS = 2 ;
    public static int GAUCHE = 3 ;
    public static int DROITE = 1 ;

    protected int width;
    protected int height;
    protected Point positions;
    protected int speed;
    protected BufferedImage bufferedImage;
    private int step = 1;
    private boolean moving;

    public Monstre(Point point, int width, int height){
        this.positions = point;
        this.width = width;
        this.height = height;
        this.moving=true;
    }

    /**
     *
     * @param labyrinthe
     * @param wallWidth
     * @param wallHeight
     */
    public void move(Labyrinthe labyrinthe, int wallWidth, int wallHeight){
        //A droite
        if(step == DROITE) {
            if(!labyrinthe.isWall(positions.x + speed + wallWidth/2, positions.y)){
                positions.x += speed;
            }
            else {
                Random rand = new Random();
                step = rand.nextInt(4-1+1) + 1;
            }
        }
        //A gauche
        else if(step == GAUCHE) {
            if(!labyrinthe.isWall(positions.x - speed - wallWidth/2, positions.y)){
                positions.x -= speed;
            }
            else {
                Random rand = new Random();
                step = rand.nextInt(4-1+1) + 1;
            }
        }
        //Haut
        else if(step == HAUT) {
            if(!labyrinthe.isWall(positions.x, positions.y - speed - wallHeight/2)){
                positions.y -= speed;
            }
            else {
                Random rand = new Random();
                step = rand.nextInt(4-1+1) + 1;
            }
        }
        //Bas
        else if(step == BAS) {
            if(!labyrinthe.isWall(positions.x, positions.y + speed + wallHeight/2)){
                positions.y += speed;
            }
            else {
                Random rand = new Random();
                step = rand.nextInt(4-1+1) + 1;
            }
        }
    }
    public void draw(BufferedImage im) throws IOException{
    }

    public void suspend(){
        moving=false;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean monstreNormal(){
        return false;
    }

    public Point getPosition() {
        return positions;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
