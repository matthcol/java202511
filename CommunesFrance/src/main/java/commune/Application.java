package commune;

import commune.model.Commune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Commune commune1 = new Commune("31555", "Toulouse", "31000", 500_000, 50.25F);
        Commune commune2 = new Commune();
        Commune commune3 = new Commune("Montauban");

//        List<Commune> listeCommune = new ArrayList<>();
//        Collections.addAll(listeCommune, commune1, commune2, commune3);
        var listeCommune = List.of(commune1, commune2, commune3); // var = List<Commune>

        for (var commune: listeCommune){  // var = Commune
            System.out.println(commune);
            System.out.println(commune.toString()); // i.e. ligne au dessus
            System.out.println("Commune: " + commune);
            System.out.println("Commune: " + commune.toString()); // i.e. ligne au dessus
            System.out.println(" - code INSEE : " + commune.getCodeInsee());
            System.out.println(" - nom standard : " + commune.getNomStandard());
        }

        commune3.setCodePostal("82000");
        System.out.println(commune3.getCodePostal());
        System.out.println(commune3);
    }
}
