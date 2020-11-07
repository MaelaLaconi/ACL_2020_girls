package model;

import engine.Cmd;
import engine.Game;
import model.etat.Degat;
import model.etat.Hero;
import model.etat.Labyrinthe;
import model.etat.Pouvoir;
import model.etat.floor.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	private int nbVieH;
	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source) throws IOException {
		lab = new Labyrinthe();
		hero = new Hero();
		BufferedReader helpReader;
		this.source=source;
 		nbVieH=hero.getNbDeVie();

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
					hero.nextFrame("up");


				}
				break;
			case DOWN:
				if(collision(0, speed)) {
					hero.move(0, speed);
					hero.nextFrame("down");
				}
				break;
			case LEFT:
				if(collision(-speed, 0)) {
					hero.move(-speed, 0);
					hero.nextFrame("left");

				}
				break;
			case RIGHT:
				if(collision(speed,0)) {
					hero.move(speed, 0);
					hero.nextFrame("right");
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
		BufferedImage imCoeur=ImageIO.read(new File("resources/images/coeur.png"));
		//Redimensionner l'image
		BufferedImage redImage=new BufferedImage(60,50,imCoeur.getType());
		infosBar.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		int dx1=450; int dx2=510;
		for (int i=0;i<nbVieH;i++){
			infosBar.drawImage(imCoeur,dx1,0,dx2,50,0,0,imCoeur.getWidth(),imCoeur.getHeight(),null);
			dx1=dx2+5;
			dx2=dx1+60;
		}

		update();
	}

	private void update() throws IOException {
		if(lab.getFloor(hero.getPosition().x, hero.getPosition().y).isAtDoor()&& lab.getStage().openDoor() ){
			this.source="resources/lab/lab1.txt";
			this.hero.setPosition(new Point(this.hero.getPosition().x/2,this.hero.getPosition().y/2));
			hero.setSaiyen(false);  //on redeviens normal a chanque nouveau map
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

		if(!this.lab.equals(null)){
			if (lab.getFloor(hero.getPosition().x, hero.getPosition().y).isMagicalStep()) {
				MagicStep magicStep = (MagicStep) lab.getFloor(hero.getPosition().x, hero.getPosition().y);
				if (!magicStep.isActivate()) {

					Pouvoir pouvoir = Pouvoir.randomPouvoir() ;
					switch (pouvoir){
						case TEMPS:
							hero.addTime();
							break;
						case SUSPEND:
							lab.suspendMonstre(5);
							break;
						case VIE:
							//hero.addLife() ;
							break;
						case SAIYAN:
							hero.setSaiyen(true);
							break;
					}
					magicStep.activate(hero);

				}
			}

			if (lab.getFloor(hero.getPosition().x, hero.getPosition().y).isTrapStep()) {
				TrapStep trapStep = (TrapStep) lab.getFloor(hero.getPosition().x, hero.getPosition().y);
				if (!trapStep.isActivate()) {
					Degat degat = Degat.randomDegat() ;
					switch (degat){
						case TEMPS:
							System.out.println("TEEEEEEEEEEMPS");
							hero.subTime();
							break;
						case VIE:
							System.out.println("VIEEEEEEEEEEE");
							//hero.subLife() ;
							break;
						case SCORE:
							System.out.println("SCOOOOOOOOOOOOORE");
							score -= 5 ;
							break;
					}
					trapStep.activate();

				}

			}


			if (lab.getFloor(hero.getPosition().x, hero.getPosition().y).isTresor()) {
				if (!this.lab.getStage().openDoor()) {
					this.lab.getStage().setBufferedImage(ImageIO.read(new File("resources/images/dooropen.png")));
					this.lab.getStage().setOpen(true);
					Tresor tresor = (Tresor) lab.getFloor(hero.getPosition().x, hero.getPosition().y);
					if (!tresor.isCollected()) {
						score=score+20;
						tresor.collected(hero);
					}
				}
			}
		}
			nbVieH=hero.getNbDeVie();

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
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label;

		//collision avec monstre normal -> on stp le jeu pour l'instant sauf si le hero est en mode saiyen
		if (lab.collisionMonstreNormal(hero.getPosition().x, hero.getPosition().y) && !hero.isSaiyen()) {
			if (hero.getNbDeVie()>0 && hero.getImunise()==false){
				hero.setImunise(true);
				hero.isImunise();
				hero.perdreUneVie();
				System.out.println(hero.getNbDeVie());
				System.out.println(hero.getImunise());
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

		/*if(lab.getFloor(hero.getPosition().x, hero.getPosition().y).tresor()){
			label = new JLabel("C'est gagné !");
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
		*/else{

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