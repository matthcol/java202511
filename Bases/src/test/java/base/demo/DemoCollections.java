package base.demo;

import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;

public class DemoCollections {

    @Test
    void demoList(){
//        List cities = new ArrayList(); // Java 1.2 => Java 1.4 ; après => warning
//        List<String> cities = new ArrayList<String>(); // Java 5 : généricité
        List<String> cities = new ArrayList<>(); // Java 7 : notation diamond
//        var cities =  new ArrayList<String>(); // Java 11
        System.out.println(cities);
        cities.add("Pau");
        Collections.addAll(cities, "Montauban", "Toulouse", "Bayonne");
        System.out.println(cities);


        List<String> cities2 = new ArrayList<>();
        cities2.add("Paris");
        cities2.addAll(cities); // reverse les éléments de cities dans cities2
        System.out.println(cities2);

        List<String> cities3 = new ArrayList<>(cities2);
        System.out.println(cities3);

        Set<String> citySet = new HashSet<>(cities3);
        System.out.println(citySet);

        System.out.println(cities.get(2));
//        System.out.println(citySet.get(0)); // n'existe pas sur les ensemble => parcours + filtrage

        cities3.add(1, "Montpellier");
        System.out.println(cities3);

        cities3.set(0, "Moissac");
        System.out.println(cities3);

        for (String city: cities3){
            System.out.println(city);
        }
    }

    @Test
    void demoListOf(){ // Java 11
        // List<String> cities = List.of("Toulouse", "Montauban", "Moissac");
        var cities = List.of("Toulouse", "Montauban", "Moissac");
        System.out.println(cities);
        System.out.println(cities.get(0));
        for (String city: cities){
            System.out.println(city);
        }
        // cities.add("Paris"); // unsupported operation: list non mutable
        System.out.println(cities.getClass()); // java.util.ImmutableCollections$ListN
    }

    // idem avec Set.of
    // - creer 1 ensemble de 3 villes
    // - l'afficher
    // - demander la classe concrète de l'ensemble
    // - vérifier qu'il est non mutable
    @Test
    void demoSet(){
        // Set<String> cities = Set.of("Toulouse", "Montauban", "Moissac");
        var cities = Set.of("Toulouse", "Montauban", "Moissac");
        Class<?> setClass = cities.getClass();
        System.out.println(setClass);
        System.out.println(cities); // java.util.ImmutableCollections$SetN
//        cities.add("Paris");
    }

    @Test
    void demoAutoboxing(){
        int a = 12;
        Integer ao = a; // boxing : new Integer(a)
        int b = ao + 12;  // unboxing: a0.intValue() + 12
    }

    @Test
    void demoListIntegers(){
        List<Integer> numbers = List.of(4, 8, 15, 16, 23, 42);
//        var numbers = List.of(4, 8, 15, 16, 23, 42);
        int nb0 = numbers.get(0);
        int total = 0;
        for (int nb: numbers) { // unboxing
            System.out.println(nb);
            total += nb;  // i.e   total = total + nb
        }
        System.out.println("Total: " + total);
    }

    @Test
    void demoSortList(){
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, "Toulouse", "Tarbes", "Montauban", "Moissac", "Paris", "Poitiers");
        Collections.shuffle(cities);
        System.out.println(cities);
        // cities.sort(1 comparator);
        Collections.sort(cities); // tri par défaut, en place
        System.out.println(cities);
    }

    @Test
    void demoSortCollation(){
        List<String> words = new ArrayList<>();
        Collections.addAll(words,
                "encore",
                "été", "étage", "étuve",
                "cobra", "corde", "cœur",
                "caleçon", "caler",
                "Zèbre", "Arbre",
                "euh"
        );
        Collections.sort(words); // A-Z puis a-z puis lettres décorées (accent, cédille, œ)
        System.out.println(words);

        Collator collatorFr = Collator.getInstance(Locale.FRENCH);
        Collections.sort(words, collatorFr);
        System.out.println(words);
    }

    @Test
    void demoSortedSet(){
        // 2 interfaces : SortedSet, NavigableSet
        Collator collatorFr = Collator.getInstance(Locale.FRENCH);
        NavigableSet<String> words = new TreeSet<>(collatorFr);
        Collections.addAll(words,
                "encore",
                "été", "étage", "étuve",
                "cobra", "corde", "cœur",
                "caleçon", "caler",
                "Zèbre", "Arbre",
                "euh"
        );
        System.out.println(words);
        words.add("wagon");
        System.out.println(words);

        // binary search
        boolean ok1 = words.contains("wagon");
        System.out.println(ok1);
        boolean ok2 = words.contains("table");
        System.out.println(ok2);

        for (String word: words){ // du + petit au + grand
            System.out.println(word);
        }
    }

    @Test
    void demoMap(){
        Map<String,Integer> indexCityDepartment = Map.of(
                "Toulouse", 31,
                "Pau", 64,
                "Montauban", 82
        );
        System.out.println(indexCityDepartment);
        for (var entry: indexCityDepartment.entrySet()){  // NB: var = Map.Entry<String,Integer>
            System.out.println(MessageFormat.format(
                    "ville = {0} ; département = {1}",
                    entry.getKey(),
                    entry.getValue()
            ));
        }

        // obtenir une valeur à partir d'une clé
        Integer department = indexCityDepartment.get("Montauban");
        System.out.println(department);
        department = indexCityDepartment.get("Paris");
        System.out.println(department);

        // ordre par défaut de String sur les clés
        var indexCityDepartment2 = new TreeMap<String, Integer>(indexCityDepartment);
        System.out.println(indexCityDepartment2);
        indexCityDepartment2.put("Montpellier", 34);
        System.out.println(indexCityDepartment2);
        indexCityDepartment2.put("Bordes", 65);
        indexCityDepartment2.put("Bordes", 64);
        System.out.println(indexCityDepartment2);

        // TODO: inverser la map : departement -> ville
        // sol 1 : Map<Integer, String>  1 ville par département
        // sol 2 : Map<Integer, List<String>> plusieurs villes par département
    }
}
