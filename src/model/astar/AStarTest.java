package model.astar;

import model.PacmanGame;
import model.etat.Hero;
import model.etat.Labyrinthe;
import start.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AStarTest {

    public static void main(String[] args) throws IOException {

        Labyrinthe laby ;
        InputStream inputStream = Main.class.getResourceAsStream("/lab/labTest.txt") ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PacmanGame game= new PacmanGame(reader);
        laby = game.getLab() ;
        int[][] blocksArray = laby.getBlocksArray();

        Hero hero = game.getHero() ;
        //Node initialNode = new Node(hero.getPosition().x, hero.getPosition().y);
        Node initialNode = new Node(2, 1);
        Node finalNode = new Node(2, 5);
        int rows = 6;
        int cols = 7;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        //int[][] blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
        System.out.println("test : ");
        for (int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 2 ; j++){
                System.out.println(blocksArray[i][j]);
            }
        }
        aStar.setBlocks(blocksArray);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }



        //Search Area
        //      0   1   2   3   4   5   6
        // 0    -   -   -   -   -   -   -
        // 1    -   -   -   B   -   -   -
        // 2    -   I   -   B   -   F   -
        // 3    -   -   -   B   -   -   -
        // 4    -   -   -   -   -   -   -
        // 5    -   -   -   -   -   -   -

        //Expected output with diagonals
        //Node [row=2, col=1]
        //Node [row=1, col=2]
        //Node [row=0, col=3]
        //Node [row=1, col=4]
        //Node [row=2, col=5]

        //Search Path with diagonals
        //      0   1   2   3   4   5   6
        // 0    -   -   -   *   -   -   -
        // 1    -   -   *   B   *   -   -
        // 2    -   I*  -   B   -  *F   -
        // 3    -   -   -   B   -   -   -
        // 4    -   -   -   -   -   -   -
        // 5    -   -   -   -   -   -   -

        //Expected output without diagonals
        //Node [row=2, col=1]
        //Node [row=2, col=2]
        //Node [row=1, col=2]
        //Node [row=0, col=2]
        //Node [row=0, col=3]
        //Node [row=0, col=4]
        //Node [row=1, col=4]
        //Node [row=2, col=4]
        //Node [row=2, col=5]

        //Search Path without diagonals
        //      0   1   2   3   4   5   6
        // 0    -   -   *   *   *   -   -
        // 1    -   -   *   B   *   -   -
        // 2    -   I*  *   B   *  *F   -
        // 3    -   -   -   B   -   -   -
        // 4    -   -   -   -   -   -   -
        // 5    -   -   -   -   -   -   -
    }
}
