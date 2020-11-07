package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GuardianMonster  extends Monstre{
    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        bufferedImage = ImageIO.read(new File("resources/images/Mojo.png"));
        speed = 4;
    }


    @Override
    public boolean monstreGuardianMonster() {
        return true;
    }
}