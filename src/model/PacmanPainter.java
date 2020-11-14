package model;


import java.awt.image.BufferedImage;
import java.io.IOException;

import engine.Game;
import engine.GamePainter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private PacmanGame pacmanGame;

	/**
	 * la taille des cases
	 */
	public static int WIDTH;
	public static int HEIGHT;

	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(PacmanGame pacmanGame) {
		this.pacmanGame = pacmanGame;
		WIDTH = this.pacmanGame.getLab().getWidth();
		HEIGHT = this.pacmanGame.getLab().getHeight();
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		pacmanGame.draw(im);

	}

	@Override
	public int getWidth() {
		return WIDTH;
	}


	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
