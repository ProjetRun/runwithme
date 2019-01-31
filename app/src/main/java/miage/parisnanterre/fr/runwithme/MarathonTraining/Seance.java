package miage.parisnanterre.fr.runwithme.MarathonTraining;

public class Seance {
    int id;
    int numSemaine;
    int numSeance;
    String typeSeance;
    String contenuSeance;
    boolean checked;
    int echauffement;
    int etiremement;
    boolean echauffement_done;
    boolean etirement_done;
    boolean running_done;
    String categorie_id;

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

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

    public int getEchauffement() {
        return echauffement;
    }

    public void setEchauffement(int echauffement) {
        this.echauffement = echauffement;
    }

    public int getEtiremement() {
        return etiremement;
    }

    public void setEtiremement(int etiremement) {
        this.etiremement = etiremement;
    }

    public boolean isEchauffement_done() {
        return echauffement_done;
    }

    public void setEchauffement_done(boolean echauffement_done) {
        this.echauffement_done = echauffement_done;
    }

    public boolean isEtirement_done() {
        return etirement_done;
    }

    public void setEtirement_done(boolean etirement_done) {
        this.etirement_done = etirement_done;
    }

    public boolean isRunning_done() {
        return running_done;
    }

    public void setRunning_done(boolean running_done) {
        this.running_done = running_done;
    }
}
