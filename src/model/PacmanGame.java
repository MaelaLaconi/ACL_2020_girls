package model;

import engine.Cmd;
import engine.Game;
import model.etat.Damage;
import model.etat.Hero;
import model.etat.Labyrinthe;
import model.etat.Power;
import model.etat.diamonds.BlueDiamond;
import model.etat.diamonds.RedDiamond;
import model.etat.floor.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	private int score;
	private TimerTask decount;
	private int sizeOfPolice = 35;
	protected Font font = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 30);
	private String source;
	private int nbLife;
	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source) throws IOException {
		lab = new Labyrinthe();
		hero = new Hero();
		BufferedReader helpReader;
		this.source=source;
		nbLife =hero.getNbLife();
		try {
			helpReader = new BufferedReader(new FileReader(this.source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				lab.generate(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
		}
		hero.setPosition(new Point(lab.getWidth()/3, lab.getHeight()/3));
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
					hero.nextFrame(hero.UP);
				}
				break;
			case DOWN:
				if(collision(0, speed)) {
					hero.move(0, speed);
					hero.nextFrame(hero.DOWN);
				}
				break;
			case LEFT:
				if(collision(-speed, 0)) {
					hero.move(-speed, 0);
					hero.nextFrame(hero.LEFT);
				}
				break;
			case RIGHT:
				if(collision(speed,0)) {
					hero.move(speed, 0);
					hero.nextFrame(hero.RIGHT);
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
		infosBar.drawString("Time: "+hero.getTime(), sizeOfPolice+ lab.WIDTH, (sizeOfPolice/2 + lab.HEIGHT)/2);
		infosBar.drawString("Scores: "+score, lab.getWidth()-((sizeOfPolice+ lab.WIDTH)*2), (sizeOfPolice/2 + lab.HEIGHT)/2);
		BufferedImage imageCoeur=ImageIO.read(new File("resources/images/coeur.png"));
		int dx1=450; int dx2=510;
		BufferedImage imgRed=new BufferedImage(50,60,imageCoeur.getType());
		infosBar.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		for(int i = 0; i< nbLife; i++){
			infosBar.drawImage(imageCoeur,dx1,0,dx2,50,0,0,imageCoeur.getWidth(),imageCoeur.getHeight(),null);
			dx1=dx2+5;
			dx2=dx1+60;
		}
		update();
	}

	private void update() throws IOException {
		//if we are at the door and we already took the safe
		if(lab.getFloor(hero).isAtDoor() && lab.getStage().openDoor() ){
			Random rand= new Random();
			int numeroLab = rand.nextInt(5-1+1)+1;
			this.source="resources/lab/lab"+numeroLab+".txt";
			this.hero.setPosition(new Point(this.hero.getPosition().x/2,this.hero.getPosition().y/2));
			hero.normalTransform(); //on redeviens normal a chanque nouveau map
			hero.setTime(hero.getTime()+20);// on lui rajoute 20sec à chaque nouvel map
			BufferedReader helpReader;
			this.lab=new Labyrinthe();
			try {
				helpReader = new BufferedReader(new FileReader(this.source));
				String ligne;
				while ((ligne = helpReader.readLine()) != null) {
					this.lab.generate(ligne);
				}
				helpReader.close();
			} catch (IOException e) {
			}

		}

		//if we are on a magical step
		if (lab.getFloor(hero).isMagicalStep()) {
			MagicStep magicStep = (MagicStep) lab.getFloor(hero);
			if (!magicStep.isActivate()) {
				Power power = Power.randomPower() ;
				switch (power){
					case TIME:
						System.out.println("TEMPS GAGNE");
						hero.addTime();
						break;
					case SUSPEND:
						System.out.println("SUSPENTION");
						lab.suspendMonster(5);
						break;
					case LIFE:
						System.out.println("VIE GAGNE");
						hero.addLife();
						break;
					case SAIYAN:
						System.out.println("MODE SAIYAN");
						hero.saiyanTransform();
						break;
				}
				magicStep.activate();
			}
		}

		//if we are on a trap step
		if (lab.getFloor(hero).isTrapStep()) {
			TrapStep trapStep = (TrapStep) lab.getFloor(hero);
			if (!trapStep.isActivate()) {
				Damage damage = Damage.randomDamage() ;
				switch (damage){
					case TIME:
						System.out.println("TEEEEEEEEEEMPS");
						hero.subTime();
						break;
					case LIFE:
						System.out.println("VIEEEEEEEEEEE");
						hero.subLife();
						break;
					case SCORE:
						System.out.println("SCOOOOOOOOOOOOORE");
						if (score-5 >=0) {
							score -= 5;
						}
						else {
							score = 0 ;
						}
						break;
				}
				trapStep.activate();

			}

		}

		//if we are on a diamond
		if( lab.getDiamond(hero)!=null){
			if (!lab.getDiamond(hero).isRedDiamond() && lab.getDiamond(hero)instanceof RedDiamond) {
				RedDiamond redDiamond = (RedDiamond) lab.getDiamond(hero);
				if (!redDiamond.isPicked()) {
					score += 10;
					redDiamond.picked();
				}
			}
			else if (!lab.getDiamond(hero).isBlueDiamond()&& lab.getDiamond(hero)instanceof BlueDiamond){
				BlueDiamond blueDiamond = (BlueDiamond) lab.getDiamond(hero);
				if (!blueDiamond.isPicked()) {
					score += 5;
					blueDiamond.picked();
				}
			}
		}

		//if we are on the safe
		if (lab.getFloor(hero).isSafe()) {
			if (!this.lab.getStage().openDoor()) {
				this.lab.getStage().setBufferedImage(ImageIO.read(new File("resources/images/dooropen.png")));
				this.lab.getStage().setOpen(true);
				Safe safe = (Safe) lab.getFloor(hero);
				if (!safe.isCollected()) {
					score=score+20;
					safe.collected();
				}
			}
		}
		nbLife =hero.getNbLife();
	}

	/***	 *
	 * @param x
	 * @param y
	 * @return true if there is a collision, false otherwise
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
	 * the game is finished if the hero doesn't have any life left ,
	 * or if the time is null
	 * @return true if the game is finished, fale otherwise
	 */
	@Override
	public boolean isFinished() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label;

		if (lab.collisionMonster(hero.getPosition().x, hero.getPosition().y) && !hero.isSaiyan()) {
			if (hero.getNbLife()>0 && hero.getImunise()==false){
					hero.subLife();
					hero.setImunise(true);
					hero.isImunise();
					return false;
			}
			if (hero.getImunise()==true){
				return false;
			}
			label = new JLabel("LE MOOOOOOOOOOOONSTRE VOUS A DEVORE");
			panel.add(label);
			frame.setContentPane(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setPreferredSize(new Dimension(100,100));
			frame.setVisible(true);
			//Pause for 1 seconds
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true ;
		}
		if(hero.getTime() == 0){
			label = new JLabel("Temps écoulé");
			panel.add(label);
			frame.setContentPane(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setPreferredSize(new Dimension(100,100));
			frame.setVisible(true);
			//Pause for 1 seconds
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return true;
		}
		else{
			return false;
		}




	}

	private void countDown(){
		hero.countDown();
	}
	public Labyrinthe getLab() {
		return lab;
	}
}