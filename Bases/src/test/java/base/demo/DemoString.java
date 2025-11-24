package base.demo;

import org.junit.jupiter.api.Test;

public class DemoString {

    @Test
    void demo1(){
        String city1 = "Montauban";
        String city2 = "Toulouse";
        String city3 = "L'Haÿ-les-Roses";
        String city4 = "東京";
        System.out.println(city1);
        System.out.println(city1.toUpperCase()); // toUpperCase : méthode du type String
        System.out.println(city3.toUpperCase());
        System.out.println(city1.toLowerCase());
        System.out.println(city3.substring(2, 5)); // Haÿ (débute à 0, endIndex exclus)
        System.out.println(city4 + " -> " + city4.toUpperCase());
    }
}
