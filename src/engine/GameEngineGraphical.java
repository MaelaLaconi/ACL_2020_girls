package engine;

import model.Menu;
import model.PacmanGame;
import model.PacmanPainter;
import model.etat.hero.Health;
import model.etat.hero.Hero;
import start.Main;

import javax.crypto.spec.PSource;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;

import static model.Menu.launcher;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;
	//public static Clip clip;
	private int bestScore;
	private Menu menu;


	/**
	 * launcher
	 */

	/**
	 * construit un moteur
	 *
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.menu = new Menu();
		bestScore=0;
	}

	public void update() throws IOException {
		this.game=new PacmanGame();
		this.gamePainter=new PacmanPainter((PacmanGame) game);
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {

		// creation de l'interface graphique
		long fpsCap = System.currentTimeMillis();
		/*AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/music/music.wav"));
		clip = AudioSystem.getClip();
		clip.open(ais);*/

		boolean test = false;
		while(true){
			while (!test) {
				if (!launcher) {
					System.out.print("");
				} else {
					menu.getFrame().dispose();
					test = true;
					this.gui = new GraphicalInterface(this.gamePainter, this.gameController);

				}
				}
			while(launcher){
				if (!this.game.isFinished()) {

					//clip.start();
					// drawing of the screen every 0.016s = 16.6ms
					if (System.currentTimeMillis() - fpsCap > (1000 / 60)) {
						// demande controle utilisateur
						Cmd c = this.gameController.getCommand();
						// fait evoluer le game
						this.game.evolve(c);
						// affiche le game
						this.gui.paint();
						// rest fpscap timer
						fpsCap = System.currentTimeMillis();
					}

				} else {
					menu.setScore(game.getBestScore());
					menu.update();
					this.gui.getF().dispose();
					test=false;
					update();
					launcher = false;





				}
			}
		}
	}
		}


