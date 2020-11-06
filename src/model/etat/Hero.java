package model.etat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero {

    private Point position;
    private int width;
    private int height;
    private BufferedImage im;
    private int time ;
    private boolean saiyen ;

    public Hero() throws IOException {
        position = new Point(0,0);
        width = 30;
        height = 30;
        im = ImageIO.read(new File("resources/images/hero.png"));
        time = 60 ;
        saiyen = false ;
    }

    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x-(width/2),position.y-(height/2),width,height,null);
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

        if (time-20>=0){
            time -= 20 ;
        }
    }

    public void countDown(){
        time--;
    }

    public boolean isSaiyen() {
        return saiyen;
    }

    public void saiyanTransform() throws IOException {
        this.saiyen = true ;
        im = ImageIO.read(new File("resources/images/saiyan.png"));
    }

    public void normalTransform() throws IOException {
        this.saiyen = false ;
        im = ImageIO.read(new File("resources/images/hero.png"));
    }
}
