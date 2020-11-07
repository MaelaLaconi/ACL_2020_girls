package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuardianMonster  extends Monstre{
    private BufferedImage[] bufferedImages;
    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        bufferedImages=new BufferedImage[100];
        nbFrame=8;
        for (int i=1;i<=8;i++) {
            if (i<=4) {
                bufferedImage[i - 1] = ImageIO.read(new File("resources/images/mechants/mojoD" + i + ".png"));
            }
            if (i>4){
                int j= i-4;
                bufferedImage[i - 1] = ImageIO.read(new File("resources/images/mechants/mojoG" + j + ".png"));
            }
        }
        speed = 4;
    }
    @Override
    public boolean monstreGuardianMonster() {
        return true;
    }
}