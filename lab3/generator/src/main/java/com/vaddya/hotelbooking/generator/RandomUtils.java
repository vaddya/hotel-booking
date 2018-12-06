package com.vaddya.hotelbooking.generator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class RandomUtils {

    public static Random random = new Random(System.currentTimeMillis());

    public static <T> T random(T[] array) {
        return array[random.nextInt(array.length)];
    }

    public static <T> T random(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static <T> T randomByPredicate(List<T> list, Predicate<T> predicate) {
        int maxIterations = 10;
        for (int i = 0; i < maxIterations; i++) {
            T t = random(list);
            if (predicate.test(t)) {
                return t;
            }
        }
        return random(list);
    }

    public static BigDecimal randomPrice() {
        return BigDecimal.valueOf(random.nextInt(15000));
    }

    public static short randomShort() {
        return (short) (random.nextInt(5) + 1);
    }


}
