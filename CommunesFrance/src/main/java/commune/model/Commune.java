package commune.model;

import java.text.MessageFormat;
import java.util.Objects;

public class Commune {

    // attributs

    private String codeInsee;
    private String nomStandard;
    private String codePostal;
    private int population;
    private float superficie;

    // constructeurs : 3 surcharges

    public Commune() {
    }

    public Commune(String nomStandard) {
        this.nomStandard = nomStandard;
    }

    public Commune(String codeInsee, String nomStandard, String codePostal, int population, float superficie) {
        this.codeInsee = codeInsee;
        this.nomStandard = nomStandard;
        this.codePostal = codePostal;
        this.population = population;
        this.superficie = superficie;
    }

    // accesseurs : getters and setters

    public String getCodeInsee() {
        return codeInsee;
    }

    public void setCodeInsee(String codeInsee) {
        this.codeInsee = codeInsee;
    }

    public String getNomStandard() {
        return nomStandard;
    }

    public void setNomStandard(String nomStandard) {
        this.nomStandard = nomStandard;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    // autres méthodes
    @Override
    public String toString() {
        return MessageFormat.format(
                "{0} ({1})",
                Objects.toString(nomStandard, "NA"),
                Objects.toString(codeInsee, "XXXXX")
        );
    }
}
