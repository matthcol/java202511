package enums.demo;

import enums.EnumJour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DemoEnumJour {

    @Test
    void demoJour(){
        EnumJour jour = EnumJour.JEUDI;
        System.out.println(jour);
    }

    @ParameterizedTest
    @EnumSource(EnumJour.class)
    @NullSource
    void demoJour2(EnumJour jour){
        System.out.println(jour);
        if (Objects.nonNull(jour)) {
            System.out.println("Ordinal: " + jour.ordinal());
            switch (jour) {
                case LUNDI:
                    System.out.println("Piscine");
                    break;
                case MARDI:
                case MERCREDI:
                    System.out.println("Patate");
                    break;
                case JEUDI:
                case VENDREDI:
                case SAMEDI:
                    System.out.println("Tricot");
                    break;
                case DIMANCHE:
                    System.out.println("Ski");
                    break;
                default:
                    System.out.println("C'est nul 🤣");
            }
        }
    }

    @Test
    void demoComparisons(){
        EnumJour jour = EnumJour.MERCREDI;
        Assertions.assertTrue(EnumJour.LUNDI.compareTo(EnumJour.MARDI) < 0);
        Assertions.assertEquals(EnumJour.MERCREDI, jour); // equals
        Assertions.assertSame(EnumJour.MERCREDI, jour);  // ==
        Assertions.assertTrue(EnumJour.MERCREDI == jour);
    }

    @Test
    void demoLoopFor(){
        for (EnumJour jour: EnumJour.values()){
            System.out.println(jour);
        }
    }

    @Test
    void demoLoopStream(){
        String jours = Arrays.stream(EnumJour.values())
                .map(jour -> jour.name().toLowerCase())
                .collect(Collectors.joining(", "));
        System.out.println(jours);
    }

    @ParameterizedTest
    @ValueSource(strings = {"LUNDI", "MERCREDI", "DIMANCHE"})
    void demoParse(String jourStr){
        EnumJour jour = EnumJour.valueOf(jourStr);
        System.out.println(jour);
    }

    @Test
    void demoMap(){
        Map<EnumJour, String> activities = new EnumMap<>(EnumJour.class);
        activities.put(EnumJour.LUNDI, "Poney");
        activities.put(EnumJour.VENDREDI, "Gym");
        System.out.println(activities);
    }
}
