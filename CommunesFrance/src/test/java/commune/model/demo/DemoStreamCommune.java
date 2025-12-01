package commune.model.demo;

import commune.model.CommuneL;
import commune.parser.CommuneParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.CsvTools;

import java.text.Collator;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DemoStreamCommune {

    List<String[]> lignes;

    @BeforeEach
    void loadData(){
        lignes = CsvTools.readCsvResource("/communes-france-2025.csv", getClass());
    }

    @Test
    void demoLireExtraitCommunes(){
        lignes.stream()
                .skip(1000)
                .limit(10)
                .forEach(ligne -> System.out.println(Arrays.toString(ligne)));
    }

    @Test
    void demoLireNomCommune(){
        lignes.stream()
                .limit(10)
                .map(ligne -> ligne[2])
                .forEach(System.out::println);
    }

    @Test
    void demoListeNomCommune(){
        var listeNom = lignes.stream()
                .limit(10)
                .map(ligne -> ligne[2])
                // .toList(); // shortcut Java 17
                .collect(Collectors.toList()); // Java 8
        System.out.println(listeNom);
    }

    @Test
    void demoCollectionNomCommune(){
        var listeNom = lignes.stream()
                .limit(10)
                .map(ligne -> ligne[2])
                .collect(Collectors.toCollection(TreeSet::new)); // ref constructeur par défaut
        System.out.println(listeNom);
    }

    @Test
    void demoCollectionCustomNomCommune(){
        var listeNom = lignes.stream()
                .limit(10)
                .map(ligne -> ligne[2])
                // .map(nom -> nom.toUpperCase())
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Collator.getInstance(Locale.FRENCH))
                ));
        System.out.println(listeNom);
    }

    @Test
    void demoFiltrerCommune(){
        var communes82 = lignes.stream()
                .map(CommuneParser::parseCsv)
                .filter(commune -> commune.getDepCode().equals("82"))
                // .peek(System.out::println)
                .toList();

        System.out.println();
        communes82.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    // nb habitants du 82
    @ParameterizedTest
    @ValueSource(strings={"82", "64", "31"})
    void demoNombreHabitantDepartement(String depCode){
        // SQL : SELECT SUM(population) FROM Commune WHERE dep_code = '82'
        var nombreHabitant = lignes.stream()
                .map(CommuneParser::parseCsv)
                .filter(commune -> commune.getDepCode().equals(depCode))
                //.peek(System.out::println)
                .mapToInt(CommuneL::getPopulation)
                //.peek(System.out::println)
                .sum();
        System.out.println(MessageFormat.format(
                "Nombre d habitants du {0} = {1}",
                depCode,
                nombreHabitant
        ));
    }

    @ParameterizedTest
    @ValueSource(strings={"82", "64", "31"})
    void demoNombreHabitantDepartementF(String depCode){
        // SQL : SELECT SUM(population) FROM Commune WHERE dep_code = '82'
        Predicate<CommuneL> predicat = commune -> commune.getDepCode().equals(depCode);
        // Function<CommuneL, Boolean> f = commune -> commune.getDepCode().equals(depCode);
        var nombreHabitant = lignes.stream()
                .map(CommuneParser::parseCsv)
                .filter(predicat)
                //.peek(System.out::println)
                .mapToInt(CommuneL::getPopulation)
                //.peek(System.out::println)
                .sum();
        System.out.println(MessageFormat.format(
                "Nombre d habitants du {0} = {1}",
                depCode,
                nombreHabitant
        ));
    }

    // compter les communes du 82
    @ParameterizedTest
    @ValueSource(strings={"82", "31", "64", "75", "13", "69", "19", "2A"})
    void demoNombreCommuneDept(String codeDept){
        long nbCommune = lignes.stream()
                .map(CommuneParser::parseCsv)
                .map(CommuneL::getDepCode)
                .filter(dept -> dept.equals(codeDept))
                .count();
        System.out.println(MessageFormat.format(
                "Nombre de commune du département {0} : {1}",
                codeDept,
                nbCommune
        ));
    }

    // liste triée (en français) des communes de moins de 10 habitants (<=10)
    @Test
    void demoCommunePasBcpHabitants(){
        int populationThreshold = 10;
        var collatorFr = Collator.getInstance(Locale.FRENCH);
        Comparator<CommuneL> comparatorCommuneDummy = (commune1, commune2) -> -1;
        Comparator<CommuneL> comparatorCommune = Comparator.comparing(CommuneL::getNomSansPronom, collatorFr);

        // extraction des communes
        var communeExtrait = lignes.stream()
                .map(CommuneParser::parseCsv)
                .filter(commune -> commune.getPopulation() <= populationThreshold)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(comparatorCommune)
                ));

        System.out.println(MessageFormat.format(
                "Nombre de communes d au plus {0} habitants : {1}",
                populationThreshold,
                communeExtrait.size()
        ));

        Function<CommuneL, String> commune2str = commune -> MessageFormat.format(
                "{0} ({1})",
                commune.getNomStandard(),
                commune.getPopulation()
        );

        System.out.println("--------------------");
        System.out.println("Ordre nom de ville: ");
        communeExtrait.stream()
                .map(commune2str)
                .forEach(System.out::println);

        System.out.println("------------------------------------");
        System.out.println("Ordre population puis nom de ville: ");
        communeExtrait.stream()
                .sorted(
                        Comparator.comparing(CommuneL::getPopulation)
                                .thenComparing(CommuneL::getNomSansPronom, collatorFr)
                )
                .map(commune2str)
                .forEach(System.out::println);

        System.out.println("------------------------------------------------");
        System.out.println("Ordre population décroissant puis nom de ville: ");
        communeExtrait.stream()
                .sorted(
                        Comparator.comparing(CommuneL::getPopulation, Comparator.reverseOrder())
                                .thenComparing(CommuneL::getNomSansPronom, collatorFr)
                )
                .map(commune2str)
                .forEach(System.out::println);
    }

    // total population par département ayant plus de xxx habitants
    @Test
    void demoGroupByHaving(){
        // SELECT
        //    dept,
        //    SUM(population) as population_dept
        // FROM Commune
        // GROUP BY dept
        // HAVING SUM(population) >= 1000000
        // ORDER BY population_dept DESC
        int populationThreshold = 1_000_000;
        var mapPopulationParDepartement = lignes.stream()
                .map(CommuneParser::parseCsv)
                // .collect(Collectors.groupingBy(CommuneL::getDepCode)); // liste de commune par dept
                .collect(
                        Collectors.groupingBy(
                                CommuneL::getDepCode,
                                Collectors.summingInt(CommuneL::getPopulation)
                        )
                );
        var populationParDepartementBig = mapPopulationParDepartement.entrySet().stream()
                .filter(entry -> entry.getValue() >= populationThreshold)
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                // .toList();
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,  // merge function (pas nécessaire ici mais requis)
                        LinkedHashMap::new  // préserve l'ordre du tri
                ));
        System.out.println(populationParDepartementBig);
    }
}
