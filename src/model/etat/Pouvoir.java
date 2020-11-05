package model.etat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Pouvoir {
    TEMPS, VIE, SAIYEN ;

    private static final List<Pouvoir> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Pouvoir randomPouvoir()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
