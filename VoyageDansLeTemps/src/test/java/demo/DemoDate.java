package demo;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DemoDate {

    @Test
    void demoDateSysteme(){
        Date d = new Date(); // Thu Nov 27 14:28:21 CET 2025
        System.out.println(d);
        System.out.println("Year : " + d.getYear()); // 125 pour 2025
        System.out.println("Month : " + d.getMonth()); // 10 pour novembre
    }

    @Test
    void demoDate(){
        Date d = new Date(124, 1, 29); // 29 février 2024
        System.out.println(d);
    }

}
