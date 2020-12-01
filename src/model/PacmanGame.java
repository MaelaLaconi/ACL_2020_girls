package model;

import engine.Cmd;
import engine.Game;
import model.astar.AStar;
import model.astar.Node;

import model.attack.Attack;
import model.attack.AttackIce;
import model.etat.lab.Difficulty;
import model.etat.lab.Labyrinthe;

import model.etat.diamonds.BlueDiamond;
import model.etat.diamonds.RedDiamond;
import model.etat.diamonds.YellowDiamond;
import model.etat.elements.*;
import model.etat.elements.potions.*;
import model.etat.hero.Damage;
import model.etat.hero.Hero;
import model.etat.hero.Power;
import model.etat.monstres.Monster;


import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;
import static model.Menu.launcher;

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
	private boolean teleport;
	private int sec,inst;
	private Difficulty difficulty;
	private boolean done=true;
	private boolean test=false;

	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(BufferedReader helpReader) throws IOException {
		lab = new Labyrinthe();
		hero = new Hero();
		difficulty = new Difficulty();
		nbLife =hero.getNbLife();
		sec=0;
		inst=0;

		try {
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
		lab.setH(hero);
	}


	@Override
	/**
	 * faire evoluer le jeu suite a une commande
	 *
	 * @param commande
	 * @return
	 */
	public void evolve(Cmd commande) {
		switch (commande) {
			case UP:
				if(hero.isNoWalls()){
					hero.moveNoCollision(3,speed);
					Attack.speed=speed;
					Attack.step=3;

				}
				else {
					if (collision(0, -speed)) {
						hero.move(0, -speed);
						hero.nextFrame(hero.UP);
					}
				}
				break;
			case DOWN:
				if(hero.isNoWalls()){
					hero.moveNoCollision(4,speed);
					Attack.step=4;
					Attack.speed=speed;
				}
				else {
					if (collision(0, speed)) {
						hero.move(0, speed);
						hero.nextFrame(hero.DOWN);
					}
				}
				break;
			case LEFT:
				if(hero.isNoWalls()){
					hero.moveNoCollision(2,speed);
					Attack.step=2;
					Attack.speed=speed;
				}
				else {

					if (collision(-speed, 0)) {
						hero.move(-speed, 0);
						hero.nextFrame(hero.LEFT);
					}
				}
				break;
			case RIGHT:
				if(hero.isNoWalls()){
					hero.moveNoCollision(1,speed);
					Attack.step=1;
					Attack.speed=speed;
				}else {

					if (collision(speed, 0)) {
						hero.move(speed, 0);
						hero.nextFrame(hero.RIGHT);
					}
				}
				break;
			case SPACE:
					test=true;


				break;
		}

		}


	public void draw(BufferedImage im) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		lab.draw(im);
		hero.draw(im);
		Graphics2D infosBar = (Graphics2D) im.getGraphics();
		infosBar.setFont(font);
		infosBar.setBackground(Color.black);
		infosBar.setColor(Color.black);
		infosBar.drawString("Time: "+hero.getTime(), sizeOfPolice+ lab.WIDTH, (sizeOfPolice/2 + lab.HEIGHT)/2);
		infosBar.drawString("Scores: "+score, lab.getWidth()-((sizeOfPolice+ lab.WIDTH)*2), (sizeOfPolice/2 + lab.HEIGHT)/2);
		BufferedImage imageCoeur = ImageIO.read(getClass().getResourceAsStream("/images/coeur.png"));

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

	private void update() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		//Si monstre attaqué il est tué
		Iterator iterator = lab.getListMonsters().iterator();
		while (iterator.hasNext()) {
			Monster monster = (Monster) iterator.next();
			if (!monster.isAlive()) {
				iterator.remove();
			}
		}


		if(!Difficulty.level.equals("1") && done){
			lab.setListMonsters(difficulty.getListMonsters());
			done=false;
		}

		if(test){
			lab.getListAttack().add(new AttackIce(new Point(hero.getPosition().x+10, hero.getPosition().y+10), 35, 35));
			test=false;
		}



		this.teleport=true;
		if(lab.getFloor(hero)!=null) {
			if (lab.getFloor(hero).isTeleportStep()) {
				for (TeleportStep teleportStep : lab.getListTeleportStep()) {
					if ((teleportStep.getX() < lab.getFloor(hero).getPosition().x || teleportStep.getX() > lab.getFloor(hero).getPosition().x + lab.getFloor(hero).getWidth()) && (teleportStep.getY() < lab.getFloor(hero).getPosition().y || teleportStep.getY() > lab.getFloor(hero).getPosition().y + lab.getFloor(hero).getHeight()) && teleport) {
						hero.setPosition(teleportStep.getPosition());
						teleport = false;
					}
				}

			}
			//if we drink the potions
			if (lab.getFloor(hero).isHpPotion()) {
				PotionHp p = (PotionHp) lab.getFloor(hero);
				hero.getHealth().setHp(5);
				p.drinkPotion();
			}

			if (lab.getFloor(hero).isSaiyanPotion()) {
				PotionSaiyan p = (PotionSaiyan) lab.getFloor(hero);
				hero.saiyanTransform();
				p.drinkPotion();
			}

			if (lab.getFloor(hero).isWallPotion()) {
				PotionWall p = (PotionWall) lab.getFloor(hero);
				hero.setNoWalls(true);
				p.drinkPotion();
			}

			if (lab.getFloor(hero).isSlowPotion()) {
				PotionSlow p = (PotionSlow) lab.getFloor(hero);
				speed = 3;
				p.drinkPotion();
			}
			//if we are at the door and we already took the safe
			if (lab.getFloor(hero).isAtDoor() && lab.getStage().openDoor()) {
				/*GameEngineGraphical.clip.close();
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/music/victory.wav"));
				GameEngineGraphical.clip = AudioSystem.getClip();
				GameEngineGraphical.clip.open(ais);
				GameEngineGraphical.clip.start();*/
				try {
					sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/*ais = AudioSystem.getAudioInputStream(new File("resources/music/music.wav"));
				GameEngineGraphical.clip = AudioSystem.getClip();
				GameEngineGraphical.clip.open(ais);
				GameEngineGraphical.clip.start();*/
				Random rand = new Random();
				int numeroLab = rand.nextInt(5 - 1 + 1) + 1;

				this.hero.setPosition(new Point(this.hero.getPosition().x / 2, this.hero.getPosition().y / 2));
				hero.normalTransform(); //on redeviens normal a chanque nouveau map
				hero.setTime(hero.getTime() + 20);// on lui rajoute 20sec à chaque nouvel map
				hero.setNoWalls(false);  //on ne peut plus passer a travers les murs
				speed = 5 ; //on reinitialise la vitesse du hero

				BufferedReader helpReader;
				InputStream inputStream = getClass().getResourceAsStream("/lab/lab" + numeroLab + ".txt");
				this.lab = new Labyrinthe();
				try {
					helpReader = new BufferedReader(new InputStreamReader(inputStream));
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
					Power power = Power.randomPower();
					switch (power) {
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
					Damage damage = Damage.randomDamage();
					switch (damage) {
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
							if (score - 5 >= 0) {
								score -= 5;
							} else {
								score = 0;
							}
							break;
					}
					trapStep.activate();

				}

			}

			//if we are on the safe
			if (lab.getFloor(hero).isSafe()) {
				if (!this.lab.getStage().openDoor()) {
					this.lab.getStage().setBufferedImage(ImageIO.read(getClass().getResourceAsStream("/images/dooropen.png")));
					this.lab.getStage().setOpen(true);
					Safe safe = (Safe) lab.getFloor(hero);
					if (!safe.isCollected()) {
						score = score + 20;
						safe.collected();
					}
				}
			}
		}
		//if we are on a diamond
		if(lab.getDiamond(hero)!=null){
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
			else if(!lab.getDiamond(hero).isYellowDiamond()&& lab.getDiamond(hero)instanceof YellowDiamond){
				YellowDiamond yellowDiamond = (YellowDiamond) lab.getDiamond(hero);
				if (!yellowDiamond.isPicked()) {
					score += 20;
					yellowDiamond.picked();
				}
			}
		}
		if(sec==50 && inst>0) {
			Runnable moveGuardian = new Runnable() {
				@Override
				public void run() {
					int[][] blocksArray = lab.getBlocksArray();
					Floor f = lab.getFloor(hero);
					int x = f.getPosition().y/ lab.HEIGHT ;
					int y = f.getPosition().x/ lab.WIDTH ;
					//System.out.println("Hero :x "+x+" y "+y);
					Node initialNode = new Node(x, y);

					x = lab.getGuardianPos().y/ lab.HEIGHT ;
					y = lab.getGuardianPos().x/ lab.WIDTH ;
					//System.out.println("Monstre draw: "+x +" "+y);

					Node finalNode = new Node(lab.getGuardianPos().y/ lab.HEIGHT, lab.getGuardianPos().x/ lab.WIDTH);
					int rows = lab.getLine()/lab.HEIGHT ;
					int cols = lab.getColumn()/lab.WIDTH ;


					AStar aStar = new AStar(rows, cols, initialNode, finalNode);

					aStar.setBlocks(blocksArray);
					List<Node> path = aStar.findPath();
					lab.getGuardianMonster().moveGuardianMonster(path);
				}
			};
			ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1) ;
			executorService.scheduleAtFixedRate(moveGuardian, 0, 200, TimeUnit.SECONDS) ;
			sec=0;
		}
		sec++;
		inst++;


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
			if (hero.getNbLife()>=0 && !hero.getImunise()){
				if(hero.getHealth().getHp()<=0 ){
					hero.subLife();
					hero.getHealth().setHp(hero.getHealth().getHealth());
				}
				else{
					hero.getHealth().damage(1);
				}

				hero.setImunise(true);
				hero.isImunise();
				return false;
			}
			if (hero.getImunise()){
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
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true ;
		}
		if(hero.getTime() <= 0){
			label = new JLabel("Temps écoulé " +
					"Votre score est de : " + score);
			panel.add(label);
			frame.setContentPane(panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setPreferredSize(new Dimension(100,100));
			frame.setVisible(true);
			//Pause for 1 seconds
			try {
				sleep(1000);
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
		if(launcher){
			hero.countDown();
		}

	}
	public Labyrinthe getLab() {
		return lab;
	}

	public Hero getHero() {
		return hero;
	}
}