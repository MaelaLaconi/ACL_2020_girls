package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;

import java.io.IOException;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		//Le labyrinthe est généré à partir d’un fichier
		PacmanGame game = new PacmanGame("resources/lab/lab.txt");
		// classe qui lance le moteur de jeu generique
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
