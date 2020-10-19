package engine;

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
	private String commande;

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
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController,String commande) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.commande=commande;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			Cmd c =Cmd.LEFT;
			if (this.commande.equals("U") || this.commande.equals("u")){
				c=Cmd.UP;
			}
			if (this.commande.equals("R") || this.commande.equals("r") ){
				c=Cmd.RIGHT;
			}
			if (this.commande.equals("D") || this.commande.equals("d") ){
				c=Cmd.DOWN;
			}

			// fait evoluer le game
			this.game.evolve(c);
			// affiche le game
			this.gui.paint();
			// met en attente
			Thread.sleep(100);
		}
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}
}