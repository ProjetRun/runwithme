package miage.parisnanterre.fr.runwithme.bottomNavigation;

import java.util.ArrayList;


public class Tips {

    public static int compteur = 0;
    private ArrayList<String> tips = new ArrayList<>();

    public Tips(){

        tips.add("Les fraises nous aident à lutter contre le vieillissement.");
        tips.add("Les bananes stimulent votre énergie.");
        tips.add("Les ananas soulagent les douleurs arthritiques.");
        tips.add("Les myrtilles renforcent votre coeur.");
        tips.add("Les pastèques favorisent la perte de poids.");
        tips.add("Courir peut aider à soulager le stress.");
        tips.add("La course à pied peut protéger le cerveau contre la maladie d’Alzheimer, même pour les personnes ayant des antécédents familiaux.");
        tips.add("Essayez de faire un jogging au lieu de vous allonger sur le canapé. Cela peut augmenter l'énergie et réduire la fatigue.");
        tips.add("Pour lutter efficacement contre les troubles du sommeil, la course à pied est un excellent somnifère naturel.");
        tips.add("Statistiquement, le risque de développer un cancer du sein est deux fois moins élevé chez les coureurs que chez les sédentaires.");

    }

    public void setCompteur(int c){
        compteur = c;
    }
    public int getCompteur(){
        return compteur;
    }
    public String getTip(){
        int  min=0;
        int  max = tips.size()-1;
        double random = (Math.random() * ((max - min) + min)) + min;
        return tips.get((int)random);
    }






}
