package test;

import model.etat.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero h ;
    @BeforeEach
    void setUp() throws IOException {
        h = new Hero();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void normalTransform() throws IOException {
        assert (!h.isSaiyan());

        h.saiyanTransform();
        assert (h.isSaiyan()) ;

        h.normalTransform();
        assert (!h.isSaiyan());

        h.normalTransform();
        assert (!h.isSaiyan());
    }

    @Test
    void saiyanTransform() {
        assert (!h.isSaiyan()) ;

        h.saiyanTransform();
        assert (h.isSaiyan()) ;

        h.saiyanTransform();
        assert (h.isSaiyan()) ;
    }

    @Test
    void move() {
        assert (h.getPosition().equals(new Point(0,0)));

        h.move(0,1);
        assert (h.getPosition().equals(new Point(0,1)));

        h.move(3,2);
        assert (h.getPosition().equals(new Point(3,3)));

        h.move(-1,-1);
        assert (h.getPosition().equals(new Point(2,2)));
    }
}