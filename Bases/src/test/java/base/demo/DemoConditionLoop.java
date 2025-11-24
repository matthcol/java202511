package base.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DemoConditionLoop {

    @ParameterizedTest
    @ValueSource(doubles = {12.2, 26.0, 26.1, 32.0})
    void demoIfOption(double temperature){
        // double temperature = 12.2;
        if (temperature > 26.0) {
            System.out.println(temperature + " : allons nous baigner 🏊👙");
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {12.2, 26.0, 26.1, 32.0})
    void demoIfElse(double temperature){
        if (temperature > 26.0) {
            System.out.println(temperature + " : allons nous baigner 🏊👙");
        } else {
            System.out.println(temperature + " : essayons le ski ⛷️🏂🚠❄️☃️");
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 9.9, 10.0, 12.2, 26.0, 26.1, 32.0})
    void demoIfMultiple(double temperature){
        if (temperature > 26.0) {
            System.out.println(temperature + " : allons nous baigner 🏊👙");
        } else if (temperature >= 10.0) {
            System.out.println(temperature + " : jardinons 🐞🐛🐌🍓🍆");
        } else {
            System.out.println(temperature + " : essayons le ski ⛷️🏂🚠❄️☃️");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "12.5, 25.5, coucou",
            "25.5, 12.5, coucou",
            "12.5, 12.5, coucou",
    })
    void testComparisons(double t1, double t2, String text){
        System.out.println(t1 == t2);
        System.out.println(t1 != t2); // différent
        System.out.println(t1 < t2);
        System.out.println(t1 <= t2);
        System.out.println(t1 > t2);
        System.out.println(t1 >= t2);
        System.out.println((10.0 <= t1) && (t1 <= 25)); // ET logique
        System.out.println((10.0 >= t1) || (t1 >= 25)); // OU logique
        System.out.println(!text.isEmpty()); // ! NOT
    }

    @Test
    void demoLoopForI(){
        // 3 clauses:
        // - initialisation
        // - test de continuité (boolean) : en entrée de boucle
        // - instruction de fin d'itération
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    void demoWhile(){
        double seed = 1.0;
        while (seed < 500.0) {
            seed *= 2.0;
        }
        System.out.println(seed);
    }

    @Test
    void demoDoWhile(){
        double seed = 1.0;
        do {
            seed *= 2.0;
        } while (seed < 500);
        System.out.println(seed);
    }
}
