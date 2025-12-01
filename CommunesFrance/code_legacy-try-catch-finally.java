InputStream in = null;
Reader reader = null;
BufferedReader bufferedReader = null;

try {
in = new FileInputStream(cheminData); // open mode binaire
reader = new InputStreamReader(in, charset); // adaptateur mode texte
bufferedReader = new BufferedReader(reader); // adaptateur mode ligne (\n, \r)

    System.out.println("Fichier: " + cheminData);
    String ligne = bufferedReader.readLine();
    while (Objects.nonNull(ligne)) {
        System.out.println(" - Ligne lue: " + ligne);
        ligne = bufferedReader.readLine();
    }
    System.out.println();

} catch (IOException e) {
// Gestion de l'exception
throw e; // rethrow

} finally {
// Fermeture dans l'ordre inverse de l'ouverture
if (bufferedReader != null) {
try {
bufferedReader.close();
} catch (IOException e) {
// Log ou ignore l'exception de fermeture
}
}
if (reader != null) {
try {
reader.close();
} catch (IOException e) {
// Log ou ignore l'exception de fermeture
}
}
if (in != null) {
try {
in.close();
} catch (IOException e) {
// Log ou ignore l'exception de fermeture
}
}
}