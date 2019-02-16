package miage.parisnanterre.fr.runwithme.settings;

import java.io.Serializable;

public class Profil implements Serializable {


    String prenom;
    String nom;
    String email;
    int sexe;
    int taille;
    int poids;

    public Profil() {
        this.prenom = "Jennifer";
        this.nom = "Carpentier";
        this.email = "jennifer.c@gmail.com";
        this.sexe = 0;
        this.taille = 66;
        this.poids = 16;
    }


    public Profil(String prenom, String nom, String email, int sexe, int taille, int poids) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.sexe = sexe;
        this.taille = taille;
        this.poids = poids;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }
}
