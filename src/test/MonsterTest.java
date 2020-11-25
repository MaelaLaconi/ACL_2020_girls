package test;

import model.etat.Lab.Labyrinthe;
import model.etat.monstres.Monster;
import model.etat.monstres.NormalMonster;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

class MonsterTest {
    private Monster monster;
    @BeforeEach
    void setUp() throws IOException {
        monster = new NormalMonster(new Point(0,0), 35, 35) ;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void move() throws IOException {
        Labyrinthe laby = new Labyrinthe() ;
        monster.move(laby, 50, 50);
    }

    @Test
    void moveGhost() {
    }

    @Test
    void moveGuardianMonster() {
    }

    @Test
    void suspend() {
    }
}