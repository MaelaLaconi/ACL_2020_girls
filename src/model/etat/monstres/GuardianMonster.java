package model.etat.monstres;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuardianMonster  extends Monster {
    private int indexImG=0,indexIm=0;

    /**
     * Constructor
     * @param point
     * @param width
     * @param height
     * @throws IOException
     */
    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        speed = 4;
        initFrame();
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        if (listedepoints.size()>2) {
            if (positions.x < listedepoints.get(listedepoints.size() - 2)) {
                    crayon.drawImage(bufferedImage[7], positions.x - (width / 2), positions.y - (height / 2), width+10, height+10, null);
                    indexIm=3;
            } else if (listedepoints.get(listedepoints.size() - 2) < positions.x) {
                    crayon.drawImage(bufferedImage[3], positions.x - (width / 2), positions.y - (height / 2), width+10, height+10, null);
                    indexIm=7;

            } else {
                crayon.drawImage(bufferedImage[indexIm], positions.x - (width / 2), positions.y - (height / 2), width+10, height+10, null);
            }
        }
        else {
            crayon.drawImage(bufferedImage[7], positions.x - (width / 2), positions.y - (height / 2), width+10, height+10, null);
        }

    }

    @Override
    public boolean monstreGuardianMonster() {
        return true;
    }

}