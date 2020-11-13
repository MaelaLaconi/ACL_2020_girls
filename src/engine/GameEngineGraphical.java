package engine;

import model.Menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

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
		launcher= Menu.launcher;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {

		// creation de l'interface graphique
		long  fpsCap = System.currentTimeMillis();
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/music/music.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		Menu menu = new Menu();
		boolean test=false;
		while(!test) {
			if (!launcher) {
				System.out.println(" ");
			}
			else{
				menu.getFrame().dispose();
				test=true;
				this.gui = new GraphicalInterface(this.gamePainter, this.gameController);

			}
		}
		while (!this.game.isFinished() && launcher) {

					clip.start();
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
				}
				System.exit(0);

	}
		}


