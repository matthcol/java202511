package base.math;

public class Euclide {

    public static int pgcd(int a, int b){
        while (a!= b){
            if (a > b){
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

}
