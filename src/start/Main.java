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
import java.net.URISyntaxException;


/**
 * lancement du moteur avec le jeu
 */
public class Main {
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException, URISyntaxException, InterruptedException {

		//Le labyrinthe est généré à partir d’un fichier
		PacmanGame game= new PacmanGame();
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}
}


