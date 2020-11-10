package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;

import java.io.*;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		//Le labyrinthe est généré à partir d’un fichier
		InputStream inputStream = Main.class.getResourceAsStream("/lab/lab.txt") ;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		PacmanGame game = new PacmanGame(reader);
		//BufferedImage image = ImageIO.read(MyClass.class.getResourceAsStream("/images/grass.png"));
		// classe qui lance le moteur de jeu generique
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
