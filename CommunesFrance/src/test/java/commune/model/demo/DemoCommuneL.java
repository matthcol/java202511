package commune.model.demo;

import commune.model.CommuneL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class DemoCommuneL {

    @Test
    void demoDefaultConstructor(){
        CommuneL commune = new CommuneL();
        System.out.println(commune);
    }

    @Test
    void buildCommune(){
        CommuneL commune1 = CommuneL.builder()
                .nomStandard("Montauban")
                .build();
        CommuneL commune2 = CommuneL.builder()
                .codeInsee("31555")
                .nomStandard("Toulouse")
                .codePostal("31000")
                .population(500_000)
                .build();
        CommuneL commune3 = CommuneL.builder()
                .codePostal("64000")
                .nomStandard("Pau")
                .depCode("64")
                .build();
        Stream.of(commune1, commune2, commune3)
                .forEach(System.out::println);

        commune1.setCodePostal("82000");
        commune1.setPopulation(50_000);
        System.out.println(commune1);
    }

    @Test
    void demoComparaisons(){
        CommuneL commune1 = CommuneL.builder()
                .codeInsee("31555")
                .nomStandard("Toulouse")
                .codePostal("31000")
                .population(500_000)
                .depCode("31")
                .build();
        CommuneL commune2 = CommuneL.builder()
                .codePostal("64000")
                .nomStandard("Pau")
                .population(77_000)
                .depCode("64")
                .build();

        System.out.println("== : " +  (commune1 == commune2)); // même objet en mémoire
        System.out.println("equals : " +  (commune1.equals(commune2))); // comparaison du type CommuneL


        // commune1 < commune2  // n'existe jamais !
        // commune1.compareTo(commune2); // < <= > >= == !=
    }

    @Test
    void testEqualString(){
        String city1 = "Toulouse";
        String city2 = "Toulouse";

        // Attention: == pour les objets compare les @ mémoire
        System.out.println(city1 == city2);
        System.out.println(city1.toUpperCase().toLowerCase() == city2.toLowerCase());

        // Utiliser méthode equals
        System.out.println(city1.equals(city2));
        System.out.println(city1.toUpperCase().toLowerCase().equals(city2.toLowerCase()));

//        System.out.println(city1 < "Montauban"); // n'existe pas
        System.out.println(city1.compareTo("Montauban"));
        System.out.println(city1.compareTo(city2));
        System.out.println("Montauban".compareTo(city1));
    }
}
