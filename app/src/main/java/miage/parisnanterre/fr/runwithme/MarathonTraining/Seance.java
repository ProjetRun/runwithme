package miage.parisnanterre.fr.runwithme.MarathonTraining;

public class Seance {
    int id;
    int numSemaine;
    int numSeance;
    String typeSeance;
    String contenuSeance;
    boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public String getContenuSeance() {
        return contenuSeance;
    }

    public void setContenuSeance(String contenuSeance) {
        this.contenuSeance = contenuSeance;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
