package model;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_LEFT:
			case 'l':
			case 'L':
				this.commandeEnCours = Cmd.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
			case 'r' :
			case 'R':
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case KeyEvent.VK_DOWN:
			case 'd' :
			case 'D':
				this.commandeEnCours = Cmd.DOWN;
				break;
			case KeyEvent.VK_UP:

			case 'u':
			case 'U':
				this.commandeEnCours = Cmd.UP;
				break;
			case KeyEvent.VK_SPACE:
			case 'a':
			case 'A':
				this.commandeEnCours = Cmd.SPACE;
				break;
			case KeyEvent.VK_ENTER:
			case 'p':
			case 'P':
				this.commandeEnCours=Cmd.PAUSE;
				break;

		}


	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
