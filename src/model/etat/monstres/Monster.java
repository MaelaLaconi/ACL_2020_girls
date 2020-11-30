package model.etat.monstres;

import model.PacmanPainter;
import model.astar.Node;
import model.etat.lab.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */

public abstract class Monster {
    public static int HAUT = 4 ;
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
    private int step ;
    private boolean moving;
    private int counterGhost;
    private int count ;
    protected Point positionprec;
    private boolean alive;

    public Monster(Point point, int width, int height){
        this.positions = point;
        this.width = width;
        this.height = height;
        bufferedImage=new BufferedImage[100];
        indexIm=0;
        this.moving=true;
        counterGhost = 0 ;
        step = 1 ;
        count = 0 ;
        listedepoints=new ArrayList<>();
        listedepoints.add(point.x);
        alive=true;
    }


    /**
     *  move normal monster (collisions with walls)
     * @param labyrinthe
     * @param wallWidth
     * @param wallHeight
     */

    public void move(Labyrinthe labyrinthe, int wallWidth, int wallHeight){
        Random rand = new Random();

        if(step == DROITE) {
            if(!labyrinthe.isWall(positions.x + speed + wallWidth/2, positions.y)){
                positions.x += speed;
            }
            else {
                step = rand.nextInt(4-1+1) + 1;
            }
        }

        else if(step == GAUCHE) {
            if(!labyrinthe.isWall(positions.x - speed - wallWidth/2, positions.y)){
                positions.x -= speed;
            }
            else {
                step = rand.nextInt(4-1+1) + 1;
            }
        }

        else if(step == HAUT) {
            if(!labyrinthe.isWall(positions.x, positions.y - speed - wallHeight/2)){
                positions.y -= speed;
            }
            else {
                step = rand.nextInt(4-1+1) + 1;
            }
        }

        else if(step == BAS) {
            if(!labyrinthe.isWall(positions.x, positions.y + speed + wallHeight/2)){
                positions.y += speed;
            }
            else {
                step = rand.nextInt(4-1+1) + 1;
            }
        }
    }

    /**
     * move gosht monster (can go through walls)
     */
    public void moveGhost(){
        counterGhost++ ;
        if(step == DROITE) {
            if ( positions.x==PacmanPainter.WIDTH) {
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

        //change direction (random)
        if (counterGhost ==200){
            Random rand = new Random();
            step = rand.nextInt(4-1+1) + 1;
            counterGhost = 0 ;
        }
    }

    /**
     *  move guardian monster toward the hero
     *
     * */
    public void moveGuardianMonster(){
        Random rand = new Random();
        step = rand.nextInt(4-1+1) + 1;

        if(step == DROITE) {
            if ( positions.x==PacmanPainter.WIDTH) {
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
    }


    public void moveGuardianMonster(List<Node> path){
        listedepoints.add(positions.x);
        if (path.size()>1) {
            positions.y = Labyrinthe.WIDTH * path.get(((path.size()-2))).getRow();
            positions.x = Labyrinthe.HEIGHT * path.get(((path.size()-2))).getCol();
        }

    }

    public void setIndexIm(int indexIm) {
        this.indexIm = indexIm;
    }

    /**
     * draw the monster on the screen
     *
     * @param im
     */
    public abstract void draw(BufferedImage im) ;

    /**
     * for trap step (the monster doesn't move some times)
     */
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
    public void initFrame() throws IOException {
        for (int i=1;i<=8;i++){
            if (i<=4){
                bufferedImage[i-1]= ImageIO.read(getClass().getResourceAsStream("/images/mechants/mojoD"+i+".png"));
            }
            else {
                bufferedImage[i-1]= ImageIO.read(getClass().getResourceAsStream("/images/mechants/mojoG"+(i-4)+".png"));
            }
        }
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

    public void setStep(int step) {
        this.step = step;
    }
}