package start;

import engine.Cmd;
import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;

import java.util.Scanner;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		Scanner sc=new Scanner(System.in);

		System.out.println("Ecrire Command(L/R/U/D/S)");
		String cmd=sc.next();


		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller,cmd);

		while (!game.isFinished()){
			System.out.println("Ecrire Command(L/R/U/D/S)");
			cmd=sc.next();
			if (cmd.equals("L")|| cmd.equals("l")){
				System.out.println("on va a gauche");
				game.evolve(Cmd.LEFT);
			}
			if (cmd.equals("R")|| cmd.equals("r")){
				System.out.println("on va a droite");
				game.evolve(Cmd.RIGHT);
			}
			if (cmd.equals("U")|| cmd.equals("u")){
				System.out.println("on va en haut");
				game.evolve(Cmd.UP);
			}
			if (cmd.equals("D")|| cmd.equals("d")){
				System.out.println("on va en bas");
				game.evolve(Cmd.DOWN);
			}
		}

			//engine.run();
	}

}
