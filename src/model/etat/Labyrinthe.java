package model.etat;

import model.etat.floor.*;
import model.etat.monstres.Fantome;
import model.etat.monstres.Monstre;
import model.etat.monstres.NormalMonstre;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */

public class Labyrinthe {
    private Collection<Floor> listFloor;
    private Collection<Monstre> listMonstres;
    private NextStage stage;
    private int ligne, colonne;
    public final int WIDTH = 50;
    public final int HEIGHT = 50;

    public Labyrinthe() throws IOException {
        listMonstres = new ArrayList<>();
        listMonstres.add(new NormalMonstre(new Point(100,100),35   ,35));
        listMonstres.add(new Fantome(new Point(300,300),35   ,35));

        listFloor = new ArrayList<>();
        ligne = 0;
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
        for(Monstre monstre : listMonstres){
            if(monstre.isMoving()) {
                monstre.move(this, WIDTH, HEIGHT);
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

    public boolean isWall(int x, int y){
        if(getFloor(x, y) instanceof Wall){
            return true;
        }else{
            return false;
        }
    }

    public Monstre getMonstre(int x, int y) {
        for (Monstre monstre: listMonstres) {
            if(monstre.getPosition().x <= x && monstre.getPosition().x+monstre.getWidth() >= x
                    && monstre.getPosition().y <= y && monstre.getPosition().y+monstre.getHeight() >= y){
                return monstre;
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
}
