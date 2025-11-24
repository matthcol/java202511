package base.demo;

import org.junit.jupiter.api.Test;

public class DemoNumerics {

    @Test
    void demoIntegers(){
        // declaration(s) avec initialisation
        int a = 2_000_000_000;
        short b = 32_000;
        long c = 9_000_000_000_000_000_000L;

        int d = (a - 10) / 8 * 3 + 1;
        System.out.println(d);
        System.out.println(2 * a); // overflow: -294967296

        System.out.println(2 * (long) a); // cast du int -> long

        System.out.println(a / 3); // division entière
        System.out.println(a % 3);  // modulo (reste de la division entière)
        System.out.println(a / 3.0); // division flottante

        // modification de variable
        d = 3 - b;
    }

    @Test
    void demoIntegerConstants(){
        int minInt = Integer.MIN_VALUE;
        System.out.println(minInt + " -> " + Integer.MAX_VALUE);
        System.out.println(Short.MIN_VALUE + " -> " + Short.MAX_VALUE);
        System.out.println(Long.MIN_VALUE + " -> " + Long.MAX_VALUE);
    }

    @Test
    void demoInitAffectationVar(){
        int a;
        a = 2 + 2;

        // variable non initialisée:
//        int b;
//        b = b + 2;

        a = a + 1;
        a++;
        ++a;
        --a;
        a--;
        a += 2; // incr 2
        a -= 3;
        a *= 2;
        a /= 2;
        a %= 10;
        System.out.println(a);
    }

    @Test
    void demoFloats(){
        double temperature = 13.5; // implicitement 1 double
        double temperature2 = 13.5D; // avec suffixe des doubles
        float speed = 70.8F;

        double res = (temperature2 + temperature) / 2;
        System.out.println(res);
    }

    @Test
    void demoSomeSingularFloats(){
        double d1 = 1E308 * 2;
        System.out.println(d1); // Infinity
        System.out.println(-d1);
        System.out.println(d1 / d1); // NaN
        System.out.println(1.0 / 0.0);
    }

    @Test
    void demoPrecisionDouble(){
        double price = 0.1; // 0.0001100110011001100110011001100110011 ...
        System.out.println(price);
        System.out.println(2 * price);
        System.out.println(3 * price);
    }

    @Test
    void demoPrecisionFloat(){
        float price = 0.1F; // 0.0001100110011001100110011001100110011 ...
        System.out.println(price);
        System.out.println(2 * price);
        System.out.println(3 * price);
        System.out.printf("%.8f", 3 * price);
    }

    // voir aussi BigDecimal pour du calcul exact avec les décimaux

    @Test
    void demoComputationFunctions(){
        // classe Math: puissance, log, trigo, arrondi
        double res = Math.round(Math.sqrt(Math.PI * 6) * 100.0) / 100.0;
        System.out.println(res);
    }
}
