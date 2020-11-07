package model.etat.monstres;

import model.PacmanGame;
import model.PacmanPainter;
import model.etat.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    protected BufferedImage[] bufferedImage;
    protected int indexIm,nbFrame;
    private int step = 1;
    private boolean moving;
    private int compteur ;

    public Monstre(Point point, int width, int height){
        this.positions = point;
        this.width = width;
        this.height = height;
        bufferedImage=new BufferedImage[100];
        indexIm=0;
        this.moving=true;
        compteur = 0 ;
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

    /**
     *
     * @param labyrinthe
     * @param wallWidth
     * @param wallHeight
     */
    public void moveGhost(Labyrinthe labyrinthe, int wallWidth, int wallHeight){
        compteur++ ;
        System.out.println("compteur = "+compteur);
        // System.out.println("X : " + positions.x +" Y : "+ positions.y + " Speed : "+speed+" Lab Height: "+ PacmanPainter.HEIGHT + " Lab Width: "+PacmanPainter.WIDTH);
        if(step == DROITE) {
            if ( positions.x==PacmanPainter.WIDTH) {
                System.out.println("here");
                 positions.x = 0 ;
            } else {
                positions.x += speed;
            }
        }

        else if(step == GAUCHE) {
            if(positions.x==0) {
                positions.x=PacmanPainter.WIDTH;
            } else {
                    positions.x -= speed;
            }
        }
        else if(step == HAUT) {
            if (positions.y <= 0) {
                positions.y = PacmanPainter.HEIGHT;
            } else {
                positions.y -= speed;
            }

        }
        else if(step == BAS) {
            if(positions.y >= PacmanPainter.HEIGHT){
                    positions.y =0;
            }
            else{
                positions.y += speed;
            }
        }

        if (compteur==200){
            Random rand = new Random();
            step = rand.nextInt(4-1+1) + 1;
            compteur = 0 ;
        }
    }

    /**
     *
     * @param labyrinthe
     * @param wallWidth
     * @param wallHeight
     */
    public void moveGuardianMonster(Labyrinthe labyrinthe, int wallWidth, int wallHeight){
        Random rand = new Random();
        step = rand.nextInt(4-1+1) + 1;
        // System.out.println("X : " + positions.x +" Y : "+ positions.y + " Speed : "+speed+" Lab Height: "+ PacmanPainter.HEIGHT + " Lab Width: "+PacmanPainter.WIDTH);
        if(step == DROITE) {
            if ( positions.x==PacmanPainter.WIDTH) {
                System.out.println("here");
                positions.x = 0 ;
               /*  while (labyrinthe.isWall(positions.x, positions.y )){
                     positions.x += speed;
                 }
                 if(!labyrinthe.isWall(positions.x, positions.y )){

                     Random rand = new Random();
                     step = rand.nextInt(4-1+1) + 1;
                 }*/

            } else {
                positions.x += speed;
                nextFrame();
                // System.out.println("here");
            }
        }

        else if(step == GAUCHE) {
            if(positions.x==0) {
                positions.x=PacmanPainter.WIDTH;
               /* while (labyrinthe.isWall(positions.x, positions.y)){
                    positions.x -= speed;
                }
                if(!labyrinthe.isWall(positions.x, positions.y )){
                   Random rand = new Random();
                    step = rand.nextInt(4-1+1) + 1;
                }
*/


            } else {
                positions.x -= speed;
                nextFrame();
            }


        }
        else if(step == HAUT) {
            if (positions.y <= 0) {
                positions.y = PacmanPainter.HEIGHT;
              /*  while (labyrinthe.isWall(positions.x, positions.y )){
                    positions.y -= speed;
                }
                if(!labyrinthe.isWall(positions.x, positions.y )){
                    Random rand = new Random();
                    step = rand.nextInt(4-1+1) + 1;
                }
*/


            } else {
                positions.y -= speed;
                nextFrame();
            }

        }
        else if(step == BAS) {
            if(positions.y >= PacmanPainter.HEIGHT){
                positions.y =0;
             /*   while (labyrinthe.isWall(positions.x, positions.y)){
                    positions.y += speed;
                }
                if(!labyrinthe.isWall(positions.x, positions.y)){

                    Random rand = new Random();
                    step = rand.nextInt(4-1+1) + 1;
                }*/




            }
            else{
                positions.y += speed;
                nextFrame();
            }

        }


    }

    public void setIndexIm(int indexIm) {
        this.indexIm = indexIm;
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    public void draw(BufferedImage im) throws IOException{
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.bufferedImage[indexIm], positions.x-(width/2), positions.y-(height/2),width,height,null);
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

    public boolean monstreFantome(){
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

    public  boolean monstreGuardianMonster(){
        return false;
    }
    public void nextFrame(){
        if (indexIm>nbFrame-1){
            indexIm=0;
        }
        else {
            indexIm++;
        }
    }
}
