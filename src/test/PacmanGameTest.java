package test;

import engine.Cmd;

import model.PacmanGame;
import model.etat.hero.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import start.Main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class PacmanGameTest {
    private Hero hero ;
    private PacmanGame pacmanGame ;
    @BeforeEach
    void setUp() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/lab/lab.txt") ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        pacmanGame = new PacmanGame() ;
        hero = pacmanGame.getHero() ;
    }

    @AfterEach
    void tearDown() {
    }

    /*@Test
    void evolve() {
        Cmd cmd = Cmd.DOWN ;
        System.out.println(hero.getPosition().x +"et"+hero.getPosition().y);
        assert (hero.getPosition().equals(new Point(333,216))) ;

        pacmanGame.evolve(cmd);
        assert (hero.getPosition().equals(new Point(333,216))) ;

        hero.move(200, 50);
        pacmanGame.evolve(cmd);
        assert (hero.getPosition().equals(new Point(533,276))) ;
    }*/

    @Test
    void isFinished() {
    }

    @Test
    void collision() {
    }
}