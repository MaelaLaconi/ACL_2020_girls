package model.etat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Hero {

    private Point position;
    public static int width;
    public static int height;
    private BufferedImage im[];
    private int time ;
    private boolean saiyen ;
    private int nbDeVie;
    private int indexPhoto;

    public void setImunise(boolean imunise) {
        this.imunise = imunise;
    }

    private boolean imunise;
    public int getNbDeVie() {
        return nbDeVie;
    }

    public Hero() throws IOException {
        position = new Point(0,0);
        width = 30;
        height = 40;
        indexPhoto=0;
        im=new BufferedImage[100];
        init();
        time = 60 ;
        saiyen = false ;
        nbDeVie=3;
        imunise=false;

    }
    public void normalTransform() throws IOException {
        this.saiyen = false ;
        init();
    }
    public void init() throws IOException {
        for (int i=1;i<=12;i++) {
            if (i<=4) {
                im[i - 1] = ImageIO.read(new File("resources/images/belle/bellesFly" + i + ".png"));
            }
            if (i==5){
                im[i-1]= ImageIO.read(new File("resources/images/belle/belleup.png"));
            }
            else if (i==6){
                im[i-1]=ImageIO.read(new File("resources/images/belle/belledown.png"));
            }
            else if (i>6 && i<=10){
                int j=i-6;
                System.out.println(j);
                im[i - 1] = ImageIO.read(new File("resources/images/belle/bellesFlyG"+j+".png"));
            }
            else if(i==11){
                im[i-1]= ImageIO.read(new File("resources/images/belle/saiyanD.png"));
            }
            else if (i==12){
                im[i-1]= ImageIO.read(new File("resources/images/belle/saiyanG.png"));
            }
        }
    }
    public void saiyanTransform() throws IOException {
        this.saiyen = true ;
        nextFrame("saiyan");
    }
    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im[indexPhoto],position.x-(width/2),position.y-(height/2),width,height,null);
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addTime(){
        time += 30 ;
    }

    public void subTime(){
        time -= 30 ;
    }

    public void countDown(){
        time--;
    }

    public boolean isSaiyen() {
        return saiyen;
    }

    public void setSaiyen(boolean saiyen) {
        this.saiyen = saiyen;
    }
    public void perdreUneVie(){
        nbDeVie--;
    }
    public void gagnerUneVie(){
        nbDeVie++;
    }
    public void isImunise(){
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
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
    public void nextFrame(String direction){
        if (direction.equals("right")){
            if (indexPhoto>=3){
                indexPhoto=0;
            }
            else {
                indexPhoto++;
            }
        }
        if (direction.equals("left")){
            if (indexPhoto>=9){
                indexPhoto=6;
            }
            else if (indexPhoto<6){
                indexPhoto=6;
                System.out.println(indexPhoto);
            }
            else {
                indexPhoto++;
            }
        }
        if (direction.equals("up")){
            if (indexPhoto!=4){
                indexPhoto=4;
            }

        }
        else if (direction.equals("down")){
            indexPhoto=5;
        }
        else if (direction.equals("saiyan")){
            indexPhoto=11;
        }
    }
}
