package miage.parisnanterre.fr.runwithme.badges;

public class Badge {
    int numero;
    String nom;

    public Badge() {
        this.numero = 0;
        this.nom = "";
    }

    public Badge(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
