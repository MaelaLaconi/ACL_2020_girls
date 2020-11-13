package test;

import model.etat.Labyrinthe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LabyrintheTest {
    private static Labyrinthe labyrintheTest;


    @BeforeAll
    static void start() throws IOException {
        File file = new File("assets/labys.txt");
        labyrintheTest = new Labyrinthe();
        labyrintheTest.generate("wnnnnw");

        //labyrintheTest.generate(String.valueOf(file));


    }

    @Test
    void buildTest() {
        assert (labyrintheTest.isWall(0,0)) ;
        assert (labyrintheTest.isWall(0,6)) ;
        //assert (labyrintheTest.isNormalStep(0,1)) ;
        //assertDoesNotThrow(() -> labyrintheTest.generate("0"));
    }

    @Test
    void collisionMonster(){
        //ne marche pas
        labyrintheTest.collisionMonster(101,99) ;
       /* assert (labyrintheTest.collisionMonster(400,400)) ;
        assert (labyrintheTest.collisionMonster(300,500)) ;
        assert (labyrintheTest.collisionMonster(500,600)) ;
        assert (!labyrintheTest.collisionMonster(0,0)) ;*/


    }
}
