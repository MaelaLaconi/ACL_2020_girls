package model.etat.lab;

import model.etat.monstres.GhostMonster;
import model.etat.monstres.GuardianMonster;
import model.etat.monstres.Monster;
import model.etat.monstres.NormalMonster;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Difficulty {
    public static String level="1";
    private Collection<Monster> listMonsters;

    /**
     * Constructor
     */
    public Difficulty(){
        listMonsters=new ArrayList<>();
    }

    public Collection<Monster> getListMonsters() throws IOException {
        int nbMonstre= Integer.parseInt(level);

        for(int i = 0 ; i < nbMonstre ; i++) {
            listMonsters.add(new NormalMonster(new Point(100+(i*10), 100+(i*10)), 35, 35));
            listMonsters.add(new GhostMonster(new Point(400+(i*10), 400+(i*10)), 35, 35));
            listMonsters.add(new GhostMonster(new Point(500+(i*10), 500+(i*10)), 35, 35));
            listMonsters.add(new GhostMonster(new Point(500+(i*10), 600+(i*10)), 35, 35));
            if(nbMonstre>1) {
                listMonsters.add(new GuardianMonster(new Point(200 + (i * 90), 260 + (i * 90)), 35, 35));
            }
        }

        return listMonsters;
    }
}
