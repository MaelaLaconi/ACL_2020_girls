package start;

import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.PacmanPainter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * lancement du moteur avec le jeu
 */
public class Main {
	public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {

		//Le labyrinthe est généré à partir d’un fichier

		InputStream inputStream = Main.class.getResourceAsStream("/lab/lab.txt") ;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		PacmanGame game= new PacmanGame(reader);
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}
}


