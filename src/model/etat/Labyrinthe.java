package model.etat;

import model.etat.floor.*;
import model.etat.monstres.Monstre;
import model.etat.monstres.NormalMonstre;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
            monstre.move(this, WIDTH, HEIGHT);
            monstre.draw(im);
        }
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

    public boolean collisionMonstreNormal(int x, int y){
        Monstre monstre = getMonstre(x, y) ;
        if (monstre == null || !monstre.monstreNormal()){
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
}
