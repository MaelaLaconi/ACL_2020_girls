package model.etat.lab;


import model.attack.Attack;
import model.etat.diamonds.BlueDiamond;
import model.etat.diamonds.Diamond;
import model.etat.diamonds.RedDiamond;
import model.etat.diamonds.YellowDiamond;
import model.etat.elements.*;
import model.etat.elements.potions.PotionHp;
import model.etat.elements.potions.PotionSaiyan;
import model.etat.elements.potions.PotionSlow;
import model.etat.elements.potions.PotionWall;
import model.etat.hero.Hero;
import model.etat.monstres.GuardianMonster;
import model.etat.monstres.Monster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */

public class Labyrinthe {
    private Collection<Floor> listFloors;
    private Collection<Monster> listMonsters;
    private Collection<Diamond> listDiamonds;
    private Collection<TeleportStep> listTeleportStep;
    private Collection<Attack> listAttack;
    private Door stage;
    private int line, column;
    private int[][] blocksArray ;
    private int nbWall ;
    private Point guardianPos ;
    private GuardianMonster guardianMonster ;
    private Difficulty difficulty;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    /**
     * constructor
     * @throws IOException
     */
    public Labyrinthe() throws IOException {
        difficulty=new Difficulty();
        listMonsters = difficulty.getListMonsters();
        listDiamonds =new ArrayList<>();
        listFloors = new ArrayList<>();
        listTeleportStep=new ArrayList<>();
        listAttack=new ArrayList<>();
        line = 0;
        nbWall = 0 ;
    }

    /**
     * Generate Lab from file
     * @param
     * @throws IOException
     */
    public void generate() throws IOException {
        BufferedReader helpReader;
        InputStream inputStream = getClass().getResourceAsStream("/lab/lab.txt");
        try {
            helpReader = new BufferedReader(new InputStreamReader(inputStream));
            String ligne;
            while ((ligne = helpReader.readLine()) != null) {
                column = 0;
                for (char ch : ligne.toCharArray()) {
                    switch (ch) {
                        //Wall
                        case 'w':
                            listFloors.add(new Wall(new Point(column, line), WIDTH, HEIGHT));
                            nbWall++;
                            break;
                        //Normal Step
                        case 'n':
                            listFloors.add(new NormalStep(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        //Magic Step
                        case 'm':
                            listFloors.add(new MagicStep(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        //Safe
                        case 't':
                            listFloors.add(new Safe(new Point(column, line), WIDTH, HEIGHT));
                            guardianPos = new Point(column, line);
                            guardianMonster = new GuardianMonster(guardianPos, 35, 35);
                            listMonsters.add(guardianMonster);
                            break;
                        //Trap Step
                        case 's': //skull trapStep
                            listFloors.add(new TrapStep(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        //Door
                        case 'c':
                            this.stage = new Door(new Point(column, line), WIDTH, HEIGHT);
                            listFloors.add(stage);
                            break;

                        //drinking potions
                        case 'd':
                            listFloors.add(new PotionHp(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'e':
                            listFloors.add(new PotionSlow(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'f':
                            listFloors.add(new PotionWall(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'g':
                            listFloors.add(new PotionSaiyan(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        //Teleport Step
                        case 'p':
                            listFloors.add(new TeleportStep(new Point(column, line), WIDTH, HEIGHT));
                            listTeleportStep.add(new TeleportStep(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'b':
                            listDiamonds.add(new BlueDiamond(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'r':
                            listDiamonds.add(new RedDiamond(new Point(column, line), WIDTH, HEIGHT));
                            break;
                        case 'y':
                            listDiamonds.add(new YellowDiamond(new Point(column, line), WIDTH, HEIGHT));
                            break;


                    }
                    column += WIDTH;
                }
                line += HEIGHT;
            }
            helpReader.close();
        } catch (IOException e) {
        }

    }


    /**
     * Generate Lab from file
     * @param string
     * @throws IOException
     */
    public void generate(String string) throws IOException {
        column = 0;
        for (char ch: string.toCharArray()) {
            switch(ch){
                //Wall
                case 'w' :
                    listFloors.add(new Wall(new Point(column, line), WIDTH, HEIGHT));
                    nbWall++ ;
                    break;
                //Normal Step
                case 'n' :
                    listFloors.add(new NormalStep(new Point(column, line), WIDTH, HEIGHT));
                    break;
                //Magic Step
                case 'm' :
                    listFloors.add(new MagicStep(new Point(column, line), WIDTH, HEIGHT));
                    break;
                //Safe
                case 't' :
                    listFloors.add(new Safe(new Point(column, line), WIDTH, HEIGHT));
                    guardianPos = new Point(column, line) ;
                    guardianMonster = new GuardianMonster(guardianPos,35   ,35) ;
                    listMonsters.add(guardianMonster);
                    break;
                //Trap Step
                case 's' : //skull trapStep
                    listFloors.add(new TrapStep(new Point(column, line), WIDTH, HEIGHT));
                    break;
                //Door
                case 'c' :
                    this.stage=new Door(new Point(column, line), WIDTH, HEIGHT);
                    listFloors.add(stage);
                    break;

                //drinking potions
                case 'd':
                    listFloors.add(new PotionHp(new Point(column, line), WIDTH, HEIGHT));
                    break;
                case 'e':
                    listFloors.add(new PotionSlow(new Point(column, line), WIDTH, HEIGHT));
                    break;
                case 'f':
                    listFloors.add(new PotionWall(new Point(column, line), WIDTH, HEIGHT));
                    break;
                case 'g':
                    listFloors.add(new PotionSaiyan(new Point(column, line), WIDTH, HEIGHT));
                    break;
                //Teleport Step
                case 'p' :
                    listFloors.add(new TeleportStep(new Point(column, line), WIDTH, HEIGHT));
                    listTeleportStep.add(new TeleportStep(new Point(column, line), WIDTH, HEIGHT));
                    break;
                case 'b':
                    listDiamonds.add(new BlueDiamond(new Point(column, line),WIDTH, HEIGHT));
                    break;
                case 'r':
                    listDiamonds.add(new RedDiamond(new Point(column, line),WIDTH, HEIGHT));
                    break;
                case 'y':
                    listDiamonds.add(new YellowDiamond(new Point(column, line),WIDTH, HEIGHT));
                    break;


            }
            column += WIDTH;
        }
        line += HEIGHT;
    }

    public void draw(BufferedImage im) throws IOException {
        for (Floor floor : listFloors) {
            floor.draw(im);
        }
        for(Diamond diamond: listDiamonds){
            diamond.draw(im);
        }
        for(Monster monster : listMonsters){
            if(monster.isMoving() && monster.monstreNormal()) {
                monster.move(this, WIDTH, HEIGHT);
            }
            if(monster.isMoving() && monster.monstreFantome()){
                monster.moveGhost();
            }
            if(monster.isMoving() && monster.monstreGuardianMonster()){
                //monster.moveGuardianMonster();
            }
            monster.draw(im);
        }
        for(Attack attack : listAttack){
            if(!attack.isDestryo()) {
                attack.move(this, WIDTH, HEIGHT);
                attack.draw(im);
            }
        }

    }

    /**
     * get floor where the hero is
     * @param hero
     * @return
     */
    public Floor getFloor(Hero hero){
        int x = hero.getPosition().x ;
        int y = hero.getPosition().y ;
        Rectangle f, h ;
        for (Floor floor: listFloors) {
            /*f = new Rectangle(floor.getPosition().x, floor.getPosition().y, WIDTH, HEIGHT) ;
            h = new Rectangle(x, y, hero.getWidth(), hero.getHeight()) ;
            if (f.intersects(h)){
                return floor ;
            }*/
            if(floor.getPosition().x <= x && floor.getPosition().x+WIDTH >= x
                    && floor.getPosition().y <= y && floor.getPosition().y+HEIGHT >= y){
                return floor;
            }
        }
        return null;
    }

    /**
     * get floor at a certain position
     * @param x
     * @param y
     * @return
     */
    public Floor getFloor(int x, int y){
        for (Floor floor: listFloors) {
            if(floor.getPosition().x <= x && floor.getPosition().x+WIDTH >= x
                    && floor.getPosition().y <= y && floor.getPosition().y+HEIGHT >= y){
                return floor;
            }
        }
        return null;
    }


    /**
     * get diamon where the hero is
     * @param hero
     * @return
     */
    public Diamond getDiamond(Hero hero){
        int x = hero.getPosition().x ;
        int y = hero.getPosition().y ;

        for (Diamond diamond: listDiamonds) {
            if(diamond.getPosition().x <= x && diamond.getPosition().x+WIDTH >= x
                    && diamond.getPosition().y <= y && diamond.getPosition().y+HEIGHT >= y){
                return diamond;
            }
        }
        return null;
    }



    /**
     *
     * @param x
     * @param y
     * @return true is the i a wall a the position (x, y), false otherwise
     */
    public boolean isWall(int x, int y){
        if(getFloor(x, y) instanceof Wall){
            return true;
        }else{
            return false;
        }
    }


    /**
     * get monster where the hero is
     * @param x
     * @param y
     * @return
     */
    public Monster getMonster(int x, int y) {
        int x1, y1 ;
        for (Monster monster : listMonsters) {
            x1 = monster.getPosition().x ;
            y1 = monster.getPosition().y ;
            Rectangle m = new Rectangle(x1,y1, monster.getWidth(), monster.getHeight()) ;
            Rectangle h = new Rectangle(x,y,Hero.width,Hero.height) ;
            if (m.intersects(h)){
                return monster;
            }
        }
        return null;
    }

    /**
     *
     * @param x
     * @param y
     * @return true if there is a collision between the hero and a monster, false otherwise
     */
    public boolean collisionMonster(int x, int y){
        Monster monster = getMonster(x, y) ;
        if (monster == null){
            return false ;
        }
        else{
            return true ;
        }
    }

    public Door getStage() {
        return stage;
    }

    public int getWidth(){
        return column;
    }

    public int getHeight(){
        return line;
    }

    /**
     * Suspend the monster for a few seconds
     * @param second
     */
    public void suspendMonster(int second) {
        for(Monster monster : listMonsters){
            monster.suspend();
        }
        Timer timer = new Timer();
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                letMonsterGo();
            }
        };
        timer.schedule(decount, second*1000);
    }

    /**
     * Making the monster moves again
     */
    private void letMonsterGo() {
        for(Monster monster : listMonsters){
            monster.setMoving(true);
        }
    }

    public  boolean isNormalStep(int x, int y){
        return getFloor(x,y).isNormalStep() ;
    }

    public Collection<TeleportStep> getListTeleportStep() {
        return listTeleportStep;
    }

    public Collection<Diamond> getListDiamonds() {
        return listDiamonds;
    }

    public int[][] getBlocksArray() {
        blocksArray = new int[nbWall][2] ;

        int i = 0 ;
        for (Floor floor: listFloors) {
            if(floor instanceof Wall){
                blocksArray[i][0] = floor.getPosition().y / HEIGHT;
                blocksArray[i][1] = floor.getPosition().x / WIDTH;
                i++ ;
            }
        }
        return blocksArray;
    }



    public Point getGuardianPos() {
        return guardianPos;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public GuardianMonster getGuardianMonster() {
        return guardianMonster;
    }

    public int getNbWall(){
        return nbWall ;
    }

    public void setListMonsters(Collection<Monster> listMonsters) {
        this.listMonsters = listMonsters;
    }

    public Collection<Monster> getListMonsters() {
        return listMonsters;
    }

    public Collection<Attack> getListAttack() {
        return listAttack;
    }
}