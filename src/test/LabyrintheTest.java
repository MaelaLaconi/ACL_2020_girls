package test;

import model.etat.Labyrinthe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LabyrintheTest {
    private static Labyrinthe labyrintheTest;


    @BeforeAll
    static void start() throws IOException {
        File file = new File("assets/labys.txt");
        labyrintheTest = new Labyrinthe();
        labyrintheTest.generate(String.valueOf(file));


    }

    @Test
    void buildTest(){
        assertDoesNotThrow(() -> labyrintheTest.generate("0"));
    }

}
