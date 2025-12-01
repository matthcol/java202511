package commune.model.demo;

import commune.model.CommuneL;
import commune.parser.CommuneParser;
import org.junit.jupiter.api.Test;
import utils.CsvTools;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoStreamStream {
    // 2 catégories de stream :
    //  - I/O streams
    //  - stream Java 8 (prog fonctionnelle)

    @Test
    void demo1(){
        try (
            Stream<String[]> stream = CsvTools.streamCsvResource("/communes-france-2025.csv", getClass())
        ) {
            stream.limit(10)
                    .map(CommuneParser::parseCsv)
                    .forEach(System.out::println);
        } // close Stream => close streams I/O
    }

    // read stream -> traitement -> collection -> write output
    @Test
    void demo2(){
        List<CommuneL> data = null;
        try (
                Stream<String[]> stream = CsvTools.streamCsvResource("/communes-france-2025.csv", getClass())
        ) {
            data = stream.map(CommuneParser::parseCsv)
                    .filter(communeL -> communeL.getSuperficieKm2() < 1.0)
                    .peek(System.out::println)
                    .toList();
        } // close Stream => close streams I/O

        // exploiter la data en mémoire
        System.out.println();
        String nomCommunes = data.stream()
                .map(CommuneL::getNomStandard)
                .collect(Collectors.joining(", "));
        System.out.println(nomCommunes);
    }

    // read/write stream
    // -> traitement : filtrer
    // -> write output (choix du format)
    @Test
    void demoReadWrite() throws IOException {
        try (
                Stream<String[]> stream = CsvTools.streamCsvResource("/communes-france-2025.csv", getClass());
                PrintWriter writer = new PrintWriter("data/communes_petite_surface.tsv", StandardCharsets.UTF_8)
        ){
            writer.println("nom_standard\tdepartement\tsuperficie_km2\tsuperficie_ha");
            stream.map(CommuneParser::parseCsv)
                    .filter(communeL -> communeL.getSuperficieKm2() < 1.0)
                    .peek(System.out::println)
                    .map(communeL -> MessageFormat.format(
                            "{0}\t{1}\t{2}\t{3}",
                            communeL.getNomStandard(),
                            communeL.getDepCode(),
                            communeL.getSuperficieKm2(),
                            communeL.getSuperficieHectare()
                    ))
                    .peek(System.out::println)
                    .forEach(writer::println)  // NB: ouf pas d'exception mandatory
            ;
        } // close streams en input et output
    }

    @Test
    void demoNoSuperficieHa(){
        CsvTools.streamCsvResource("/communes-france-2025.csv", getClass())
                .map(CommuneParser::parseCsv)
                .filter(communeL -> Float.isNaN(communeL.getSuperficieHectare()))
                .forEach(System.out::println);
    }

}
