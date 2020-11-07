package model.etat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Damage {
    TEMPS, VIE, SCORE ;

    private static final List<Damage> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     *
     * @return a random damage
     */
    public static Damage randomDegat()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
