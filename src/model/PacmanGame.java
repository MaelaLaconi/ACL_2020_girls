package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import engine.Cmd;
import engine.Game;
import model.etat.Hero;
import model.etat.Labyrinthe;
import model.etat.floor.MagicStep;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private Hero hero;
	private Labyrinthe lab;
	private int speed = 5;
	private int time;
	private int score;
	private TimerTask decount;

	private int sizeOfPolice = 30;
	Font font = new Font("HelveticaNeue", Font.PLAIN, sizeOfPolice);

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) throws IOException {
		hero = new Hero();
		lab = new Labyrinthe();
		time = 60;
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				lab.generate(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		hero.setPosition(new Point(lab.getWidth()/2, lab.getHeight()/2));
		score=0;
		Timer timer = new Timer();
		decount = new TimerTask() {
			@Override
			public void run() {
				countDown();
			}
		};
		timer.schedule(decount, 100, 1000);
	}

	private void countDown(){
		time--;
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		switch (commande){
			case UP:
				if(collision(0, -speed)) {
					hero.move(0, -speed);
				}
				break;
			case DOWN:
				if(collision(0, speed)) {
					hero.move(0, speed);
				}
				break;
			case LEFT:
				if(collision(-speed, 0)) {
					hero.move(-speed, 0);
				}
				break;
			case RIGHT:
				if(collision(speed,0)) {
					hero.move(speed, 0);
				}
				break;
		}
	}

	public void draw(BufferedImage im) throws IOException {
		lab.draw(im);
		hero.draw(im);
		Graphics2D infosBar = (Graphics2D) im.getGraphics();
		infosBar.setFont(font);
		infosBar.setBackground(Color.black);
		infosBar.setColor(Color.black);
		infosBar.drawString("Time: "+time, sizeOfPolice+ lab.WIDTH, (sizeOfPolice/2 + lab.HEIGHT)/2);
		infosBar.drawString("Scores: "+score, lab.getWidth()-((sizeOfPolice+ lab.WIDTH)*2), (sizeOfPolice/2 + lab.HEIGHT)/2);

		update();
	}

	private void update() throws IOException {
		if(lab.getFloor(hero.getPosition().x, hero.getPosition().y).isMagicalFloor()){
			MagicStep magicStep = (MagicStep) lab.getFloor(hero.getPosition().x, hero.getPosition().y);
			if(!magicStep.isActivate()){
				magicStep.activate(hero);
			}
		}
	}

	/***	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean collision(int x, int y){
		if(x < 0 && y == 0) {
			return (!lab.isWall(
					hero.getPosition().x + x - hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2
			) &&!lab.isWall(
					hero.getPosition().x + x - hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2
			));

		}else if(x > 0 && y == 0 ){
			return (!lab.isWall(
					hero.getPosition().x + x + hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2
			) &&!lab.isWall(
					hero.getPosition().x + x + hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2
			));

		}else if(x == 0 && y > 0){
			return !lab.isWall(
					hero.getPosition().x + x,
					hero.getPosition().y + y + hero.getHeight()/2
			);

		}else{
			return !lab.isWall(
					hero.getPosition().x + x ,
					hero.getPosition().y + y - hero.getHeight()/2
			);
		}
	}

	/**
	 *
	 */
	@Override
	public boolean isFinished() {
		if(lab.getFloor(hero.getPosition().x, hero.getPosition().y).tresor() || time == 0){
			return true;
		}else{
			return false;
		}
	}

	public Labyrinthe getLab() {
		return lab;
	}
}
