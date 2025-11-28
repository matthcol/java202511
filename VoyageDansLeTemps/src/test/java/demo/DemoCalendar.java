package demo;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class DemoCalendar {

    @Test
    void demoCalendar(){
        Calendar d = Calendar.getInstance(); // date systeme en tant que GregorianCalendar
        System.out.println(d);
        System.out.println(d.getClass());
        System.out.println(d.get(Calendar.YEAR)); // 2025
        System.out.println(d.get(Calendar.MONTH)); // 10 pour novembre !
    }
}
