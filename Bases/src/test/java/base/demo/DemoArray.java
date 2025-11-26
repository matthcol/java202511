package base.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.util.Arrays;

public class DemoArray {

    @Test
    void demoStaticArray(){
        int[] numbers = {4, 8, 15, 16, 23, 42};
        System.out.println(numbers);
        System.out.println(Arrays.toString(numbers));
        System.out.println(numbers[0]);
        numbers[0] = 666;
        System.out.println(Arrays.toString(numbers));

        System.out.println("Nombres:");
        // boucle for-i
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("\t - " + numbers[i]);
        }

        System.out.println("Nombres:");
        // boucle for-each (Java 5)
        for (int number : numbers) {
            System.out.println("\t - " + number);
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"Pau", "Toulouse", "Montauban"})
    void demo(String city){
        System.out.println(city);
    }

    @ParameterizedTest
    @ValueSource(ints={0, 1, 2, 10, 1_000_000_000})
    void demoDynamicArray(int taille){
        double[] temperatures = new double[taille]; // allocation d'1 tableau dynamique
        // TODO: fill array
        System.out.println(MessageFormat.format(
                "Taille = {0} ; case#0 = {1}",
                temperatures.length,
                (temperatures.length > 0) ? temperatures[0] : "no data"
        ));
    }
}











