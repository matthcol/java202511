package commune;

import commune.model.Commune;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Application {

    private static void scenario1(){
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

    private static void scenarioLecture(String cheminData, Charset charset) throws IOException {
        try (
            InputStream in = new FileInputStream(cheminData); // open mode binaire
            Reader reader = new InputStreamReader(in, charset); // adaptateur mode texte
            BufferedReader bufferedReader =  new BufferedReader(reader); // adaptateur mode ligne (\n, \r)
        ) {
            System.out.println("Fichier: " + cheminData);
            String ligne = bufferedReader.readLine();
            while (Objects.nonNull(ligne)) {
                System.out.println(" - Ligne lue: " + ligne);
                ligne = bufferedReader.readLine();
            }
            System.out.println();
        } // auto-close dans tous les scenario (nominal et erreur)
    }

    private static void scenarioFilePath(String chemin){
        File file = new File(chemin);
        System.out.println("File: " + file);
        System.out.println(" - existe : " + file.exists());
        System.out.println(" - est un fichier régulier : " + file.isFile());
        System.out.println(" - est un répertoire : " + file.isDirectory());
        System.out.println();

        // passerelle: java.io et java.nio
        Path path1 = file.toPath();
        System.out.println("Path from file: " + path1);
        file = path1.toFile();
        System.out.println();

        // creation du path
        Path path2 = Path.of(chemin);
        System.out.println("Path : " + path2);
        System.out.println(" - filename : " + path2.getFileName());
        System.out.println(" - est 1 chemin absolu : " + path2.isAbsolute());
        System.out.println(" - parent : " + path2.getParent());
        System.out.println(" - chemin absolu : " + path2.toAbsolutePath());
        System.out.println(" - existe : " + Files.exists(path2));
        System.out.println();
    }

    private static void scenarioEcriture() throws IOException {
        Path directory = Path.of("output");
        String dayDirectoryName = LocalDate.now().toString();
        Path directoryDay = directory.resolve(dayDirectoryName);
        Path pathData = directoryDay.resolve("communes.txt");
        System.out.println(pathData);
        System.out.println();

        if (!Files.exists(directoryDay)){
            Files.createDirectories(directoryDay);
        }

        try ( // version NIO
            BufferedWriter internalWriter = Files.newBufferedWriter(pathData, StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(internalWriter);
        ){
            writer.println("Toulouse");
            writer.println("L'Haÿ-les-Roses");
            writer.println("--🦜🍻🐞☃️--");
        } // auto-close
    }

    public static void main(String[] args) throws IOException {
        // scenario1();
        String cheminData1 = "data/communes.txt";
        String cheminData2 = "data/communes1252.txt";

        scenarioLecture(cheminData1, StandardCharsets.UTF_8);
        scenarioLecture(cheminData2, Charset.forName("windows-1252"));

        scenarioFilePath(cheminData1);
        scenarioFilePath("data");
        scenarioFilePath("output");

        scenarioEcriture();
    }


}






















