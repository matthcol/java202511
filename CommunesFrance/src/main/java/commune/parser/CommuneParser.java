package commune.parser;

import commune.model.CommuneL;

public class CommuneParser {

    public static CommuneL parseCsv(String[] ligne){
        return CommuneL.builder()
                .codeInsee(ligne[1])
                .nomStandard(ligne[2])
                .nomSansPronom(ligne[3])
                .depCode(ligne[12])
                .codePostal(ligne[20])
                .population(Integer.parseInt(ligne[29]))
                .superficieKm2(Float.parseFloat(ligne[31]))
                .superficieHectare(ligne[32].isEmpty() ? Float.NaN: Float.parseFloat(ligne[32]))
                .build();
    }
}
