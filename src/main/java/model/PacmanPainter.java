package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import engine.GamePainter;

import javax.swing.text.Position;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private Graphics2D crayon;
	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 100;
	protected static final int HEIGHT = 100;
	private PacmanGame pacmanGame;
	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame pacmanGame) {
		this.pacmanGame=pacmanGame;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		crayon = (Graphics2D) im.getGraphics();
		for(int i=0; i<getWidth();i++){
			crayon.setColor(Color.GRAY);
			crayon.fillRect(i,0,5,5);
			pacmanGame.ajouterCol(i,0);
			crayon.fillRect(i,getHeight()-5,5,5);
			pacmanGame.ajouterCol(i,getHeight()-5);
		}
		for (int j=0; j<getHeight();j++){
			for(int i=0; i<getWidth()-5;i++){
				crayon.setColor(Color.GRAY);
				crayon.fillRect(0,j,5,5);
				pacmanGame.ajouterCol(0,j);
				crayon.fillRect(getWidth()-5,j,5,5);
				pacmanGame.ajouterCol(getWidth()-5,j);
			}
		}
		crayon.setColor(Color.blue);
		crayon.fillOval(pacmanGame.getX(),pacmanGame.getY(),10,10);

		crayon.setColor(Color.GRAY);
		crayon.fillRect(50,20,5,5);
		crayon.setColor(Color.GRAY);
		crayon.fillRect(20,70,15,15);
		crayon.setColor(Color.GRAY);
		crayon.fillRect(50,50,10,10);
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
