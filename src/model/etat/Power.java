package model.etat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Power {
    TIME, LIFE, SUSPEND, SAIYAN;

    private static final List<Power> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     *
     * @return a ramdom power
     */
    public static Power randomPower()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
