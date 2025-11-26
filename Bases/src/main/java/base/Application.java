package base;

import base.math.Euclide;

// classe principale
public class Application {

    // point d'entrée du programme
    public static void main(String[] args) {
        System.out.println("Bonjour, c'est lundi 😊");

        // variable de type texte = String
        String city = "Montauban";

        // variable numérique entier: int
        int nbPerson = 4;

        System.out.println("nous sommes " + nbPerson + " à " + city); // concaténation

        int g = Euclide.pgcd(21, 15);
        System.out.println("Le pgcd est : " + g);
    }

}
