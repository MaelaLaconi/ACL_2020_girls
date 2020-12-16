package model.etat.hero;

public class Health {
    private int hp;
    private int health;
    private Hero hero;

    /**
     * constructor
     * @param health
     * @param hero
     */
    public Health(int health,Hero hero){
        this.health=health;
        this.hp=health;
        this.hero=hero;
    }

    public void heal(int heal){
        while (hp < health && heal !=0) {
            hp += 1;
            heal--;
        }
    }

    /**
     * soustrait damage de hp
     * @param damage
     */
    public void damage (int damage){
        if(hp - damage >= 0) {
            hp -= damage;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getHealth() {
        return health;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
