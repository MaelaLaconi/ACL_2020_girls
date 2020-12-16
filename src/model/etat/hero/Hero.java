package model.etat.hero;

import model.PacmanPainter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Hero {
    public static String character="Belle";
    private Point position;
    public static int width;
    public static int height;
    private BufferedImage im[],imBulle[];
    private int time ;
    private boolean saiyan;
    private int nbLife;
    private int indexPhoto;
    public static int RIGHT = 1 ;
    public static int LEFT = 2 ;
    public static int UP = 3 ;
    public static int DOWN = 4 ;

    private boolean imunise;
    private boolean noWalls ;
    private BufferedImage lifeBar;
    private Health health;
    public static int direction;

    public Hero() throws IOException {
        noWalls = false ;
        position = new Point(0,0);
        width = 30;
        height = 40;
        indexPhoto=0;
        im=new BufferedImage[100];
        imBulle=new BufferedImage[100];
        lifeBar = ImageIO.read(getClass().getResourceAsStream("/images/lifebar.png"));
        init();

        time = 60 ;
        saiyan = false ;
        nbLife =3;
        imunise=false;
        this.health= new Health(5,this);
    }

    /**
     *  the hero beacomes again normal (isn't saiyan anymore)
     * @throws IOException
     */
    public void normalTransform() throws IOException {
        this.saiyan = false ;
        init();
    }

    /**
     * initialse le tableau des frames
     * @throws IOException
     */
    public void init() throws IOException {
        switch (character) {
            case "Belle":
                for (int i = 1; i <= 12; i++) {
                    if (i <= 4) {
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/bellesFly" + i + ".png"));
                    }
                    if (i == 5) {
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/belleup.png"));
                    } else if (i == 6) {
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/belledown.png"));
                    } else if (i > 6 && i <= 10) {
                        int j = i - 6;
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/bellesFlyG" + j + ".png"));
                    } else if (i == 11) {
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/saiyanD.png"));
                    } else if (i == 12) {
                        im[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/saiyanG.png"));
                    }
                }
            case "Bulle":
                for (int i = 1; i <= 14; i++) {
                    if (i <= 5) {
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/bulle/bulleD"+i+".png"));
                    }
                    if (i == 6) {
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/bulle/bulleup.png"));
                    } else if (i == 7) {
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/bulle/bullDown.png"));
                    } else if (i > 7 && i <= 12) {
                        int j = i - 7;
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/bulle/bulleG"+j+".png"));
                    } else if (i == 13) {
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/saiyanD.png"));
                    } else if (i == 14) {
                        imBulle[i - 1] = ImageIO.read(getClass().getResourceAsStream("/images/belle/saiyanG.png"));
                    }
                }
        }
    }

    /**
     *  saiyan mode activated
     */
    public void saiyanTransform() {
        this.saiyan = true ;
    }

    /**
     * draw the hero
     * @param im
     */
    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        if(character.equals("Belle")){
            crayon.drawImage(this.im[indexPhoto], position.x - (width / 2), position.y - (height / 2), width, height, null);
        }
        else{
            crayon.drawImage(this.imBulle[indexPhoto], position.x - (width / 2), position.y - (height / 2), width, height, null);
        }

        //ici lidée c'est que une fois touché on enelve de la taille de la taille de l'image
        float damage = (float) this.getHealth().getHp() / (float) this.getHealth().getHealth();
        crayon.drawImage(
                lifeBar,
                position.x-(width/2) ,position.y-(height/2) + 2,
                (int)(width * damage),height/4,
                null
        );
    }

    /**
     * fait avancer le hero a la position (x,y)
     * @param x
     * @param y
     */
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    /**
     * ajoute 30s au temps existant
     */
    public void addTime(){
        time += 30 ;
    }

    /**
     * soustrait 30s du temps existant
     */
    public void subTime(){
        time -= 30 ;
    }

    public void countDown(){
        time--;
    }

    public boolean isSaiyan() {
        return saiyan;
    }

    public int getNbLife() {
        return nbLife;
    }

    public void setImunise(boolean imunise) {
        this.imunise = imunise;
    }




    /**
     * the hero loose a life
     */
    public void subLife(){
        nbLife--;
    }

    /**
     * the hero win a life if he doesn't have 3 already
     */
    public void addLife(){
        if (nbLife < 3){
            nbLife++;
        }
    }

    /**
     * le hero est imunisé pour une période 1000;
     */
    public void isImunise(){
        TimerTask task = new TimerTask() {
            public void run() {
                // System.out.println("Task performed on: " + new Date() + "n" +
                //       "Thread's name: " + Thread.currentThread().getName());
                setImunise(false);
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        timer.schedule(task, delay);
    }

    public boolean getImunise(){
        return imunise;
    }

    /**
     * change la frame de l'image du hero
     * @param direction permet de savoir quelle direction pour avoir la bonne frame
     */
    public void nextFrame(int direction){
        this.direction=direction;
        switch (character) {
            case "Belle":
                if (direction == RIGHT) {
                    if (indexPhoto >= 3) {
                        indexPhoto = 0;
                    } else {
                        indexPhoto++;
                    }

                }
                if (direction == LEFT) {
                    if (indexPhoto >= 9) {
                        indexPhoto = 6;
                    } else if (indexPhoto < 6) {
                        indexPhoto = 6;
                    } else {
                        indexPhoto++;
                    }


                }
                if (direction == UP) {
                    if (indexPhoto != 4) {
                        indexPhoto = 4;
                    }
                } else if (direction == DOWN) {
                    indexPhoto = 5;
                }

                if (saiyan) {
                    if (direction == RIGHT) {
                        indexPhoto = 10;
                    }
                    if (direction == LEFT) {
                        indexPhoto = 11;
                    }
                }
                break;
            case "Bulle":
                if (direction == RIGHT) {
                    if (indexPhoto >= 4) {
                        indexPhoto = 0;
                    } else {
                        indexPhoto++;
                    }

                }
                if (direction == LEFT) {
                    if (indexPhoto >= 11) {
                        indexPhoto = 6;
                    } else if (indexPhoto < 6) {
                        indexPhoto = 6;
                    } else {
                        indexPhoto++;
                    }


                }
                if (direction == UP) {
                    if (indexPhoto != 5) {
                        indexPhoto = 5;
                    }
                } else if (direction == DOWN) {
                    indexPhoto = 6;
                }

                if (saiyan) {
                    if (direction == RIGHT) {
                        indexPhoto = 12;
                    }
                    if (direction == LEFT) {
                        indexPhoto = 13;
                    }
                }
                break;
        }
    }


    public Health getHealth() {
        return health;
    }

    /**
     * traverser les murs
     * @param step
     * @param speed
     */
    public void moveNoCollision(int step, int speed){
        if(step == RIGHT) {
            if ( position.x== PacmanPainter.WIDTH) {
                position.x = 0 ;
            } else {
                position.x += speed;
            }
        }

        else if(step == LEFT) {
            if(position.x==0) {
                position.x=PacmanPainter.WIDTH;
            } else {
                position.x -= speed;
            }
        }

        else if(step == UP) {
            if (position.y <= 0) {
                position.y = PacmanPainter.HEIGHT;
            } else {
                position.y -= speed;
            }

        }

        else if(step == DOWN) {
            if(position.y >= PacmanPainter.HEIGHT){
                position.y =0;
            }
            else{
                position.y += speed;
            }
        }
        nextFrame(step);
    }

    public boolean isNoWalls() {
        return noWalls;
    }

    public void setNoWalls(boolean noWalls) {
        this.noWalls = noWalls;
    }

    public void setNbLife(int nbLife) {
        this.nbLife = nbLife;
    }

    public void setHealth(Health health) {
        this.health = health;
    }
}