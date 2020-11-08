package model.etat;

import model.etat.diamonds.Diamond;
import model.etat.diamonds.DiamondBleu;
import model.etat.diamonds.DiamondRouge;
import model.etat.floor.*;
import model.etat.monstres.Fantome;
import model.etat.monstres.GuardianMonster;
import model.etat.monstres.Monstre;
import model.etat.monstres.NormalMonstre;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 *
 */

public class Labyrinthe {
    private Collection<Floor> listFloor;
    private Collection<Monstre> listMonstres;
    private Collection<Diamond> listDiamond;
    private NextStage stage;
    private int ligne, colonne;
    public final int WIDTH = 50;
    public final int HEIGHT = 50;
    private Labyrinthe lab;
    public Labyrinthe() throws IOException {
        listMonstres = new ArrayList<>();
        listMonstres.add(new NormalMonstre(new Point(100,100),35   ,35));
        listMonstres.add(new Fantome(new Point(400,400),35   ,35));
        listDiamond =new ArrayList<>();
        listDiamond.add(new DiamondBleu(new Point(200,300),35   ,35));
        listDiamond.add(new DiamondRouge(new Point(450,150),30   ,30));


        listFloor = new ArrayList<>();
        ligne = 0;
        lab=this;
    }

    /**
     * Generate Lab from file
     * @param string
     * @throws IOException
     */
    public void generate(String string) throws IOException {
        colonne = 0;
        for (char ch: string.toCharArray()) {
            switch(ch){
                case 'w' :
                    listFloor.add(new Wall(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'n' :
                    listFloor.add(new NormalStep(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'm' :
                    listFloor.add(new MagicStep(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 't' :
                    listFloor.add(new Tresor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    listMonstres.add(new GuardianMonster(new Point(colonne,ligne),35   ,35));
                    break;
               case 's' : //skull trapStep
                    listFloor.add(new TrapStep(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'c' :
                    this.stage=new NextStage(new Point(colonne, ligne), WIDTH, HEIGHT);
                    listFloor.add(stage);
                    break;
                case 'p' :
                    listFloor.add(new TeleportStep(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;

            }
            colonne += WIDTH;
        }
        ligne += HEIGHT;
    }

    public void draw(BufferedImage im) throws IOException {
        for (Floor floor : listFloor) {
            floor.draw(im);
        }
        for(Diamond diamond: listDiamond){
            diamond.draw(im);
        }
        for(Monstre monstre : listMonstres){
            if(monstre.isMoving() && monstre.monstreNormal()) {
                monstre.move(this, WIDTH, HEIGHT);
            }
            if(monstre.isMoving() && monstre.monstreFantome()){
                monstre.moveGhost(this, WIDTH, HEIGHT);
            }
            if(monstre.isMoving() && monstre.monstreGuardianMonster()){
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println("Task performed on: " + new Date() + "n" +
                                "Thread's name: " + Thread.currentThread().getName());
                        monstre.moveGuardianMonster(lab, WIDTH, HEIGHT);
                    }
                };
                Timer timer = new Timer("Timer");

                long delay = 10000L;
                timer.schedule(task, delay);

            }
            monstre.draw(im);
        }
    }

    public Floor getFloor(Hero hero){
        int x = hero.getPosition().x ;
        int y = hero.getPosition().y ;

        for (Floor floor: listFloor) {

            if(floor.getPosition().x <= x && floor.getPosition().x+WIDTH >= x
            && floor.getPosition().y <= y && floor.getPosition().y+HEIGHT >= y){
                return floor;
            }
        }
        return null;
    }

    public Floor getFloor(int x, int y){
        for (Floor floor: listFloor) {
            if(floor.getPosition().x <= x && floor.getPosition().x+WIDTH >= x
                    && floor.getPosition().y <= y && floor.getPosition().y+HEIGHT >= y){
                return floor;
            }
        }
        return null;
    }

    public Diamond getDiamond(Hero hero){
        int x = hero.getPosition().x ;
        int y = hero.getPosition().y ;

        for (Diamond diamond: listDiamond) {
            if(diamond.getPosition().x <= x && diamond.getPosition().x+WIDTH >= x
                    && diamond.getPosition().y <= y && diamond.getPosition().y+HEIGHT >= y){
                return diamond;
            }
        }
        return null;
    }

    public Diamond getDiamond(int x, int y){
        for (Diamond diamond: listDiamond) {
            if(diamond.getPosition().x <= x && diamond.getPosition().x+WIDTH >= x
                    && diamond.getPosition().y <= y && diamond.getPosition().y+HEIGHT >= y){
                return diamond;
            }
        }
        return null;
    }
    public boolean isWall(int x, int y){
        if(getFloor(x, y) instanceof Wall){
            return true;
        }else{
            return false;
        }
    }

    public Monstre getMonstre(int x, int y) {
        int x1, y1 ;
        for (Monstre monstre: listMonstres) {
            x1 = monstre.getPosition().x ;
            y1 = monstre.getPosition().y ;
            Rectangle m = new Rectangle(x1,y1, monstre.getWidth(), monstre.getHeight()) ;
            Rectangle h = new Rectangle(x,y,Hero.width,Hero.height) ;
            if (m.intersects(h)){
                return monstre ;
            }

        }
        return null;
    }

    public boolean collisionMonstre(int x, int y){
        Monstre monstre = getMonstre(x, y) ;
        if (monstre == null){
            return false ;
        }
        else{
            return true ;
        }
    }

    public NextStage getStage() {
        return stage;
    }

    public int getWidth(){
        return colonne;
    }

    public int getHeight(){
        return ligne;
    }

    /**
     * Suspend le monstre pour quelques secondes
     * @param second
     */
    public void suspendMonstre(int second) {
        for(Monstre monstre : listMonstres){
            monstre.suspend();
        }
        Timer timer = new Timer();
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                letMonstreGo();
            }
        };
        timer.schedule(decount, second*1000);
    }

    /**
     * Making the monster moves again
     */
    private void letMonstreGo() {
        for(Monstre monster : listMonstres){
            monster.setMoving(true);
        }
    }

    public Collection<Diamond> getListDiamond() {
        return listDiamond;
    }
}
