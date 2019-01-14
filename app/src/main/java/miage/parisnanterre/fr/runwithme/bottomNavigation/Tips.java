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
        tips.add("La course à pied lente et facile vous aide à développer et à maintenir votre forme physique tout en permettant à votre corps de récupérer des séances plus difficiles.");
        tips.add("Courir plus muscle le coeur.");
        tips.add("La diversité de l’entrainement reste pourtant un facteur essentiel de progression.");
        tips.add("Privilégiez la régularité à la vitesse, écoutez vos sensations, ne vous mettez pas dans le rouge et prenez le temps de vous échauffer.");
        tips.add("Courir est excellent pour faire baisser son taux de cholestérol.");
        tips.add("Un échauffement doit être d’au moins 10 minutes et plus encore en hiver.");
        tips.add("Les dernières études scientifiques démontrent clairement qu’en effet le running est excellent pour le cerveau et pour le corps en général.");
        tips.add("Pour ressentir éventuellement le plaisir et donc la sécrétion d’endorphines, il faut courir au moins 45 minutes.");
        tips.add("Évitez de manger uniquement du cru le soir pour faciliter la digestion !");
        tips.add("Une alimentation variée couvre la quasi-totalité des besoins vitaminiques et minéraux.");
        tips.add("L’hydratation et l’alimentation pendant l’effort de la course à pied seront des facteurs clés pour éviter la déshydratation et la baisse d’énergie donc la baisse de performance.");
        tips.add("L’alcool réduit les capacités physiques et va augmenter les toxines que l’organisme devra éliminer.");
        tips.add("Plus la durée de la course est longue et plus la quantité d’alimentation durant l’effort doit être importante, la quantité en eau également.");
        tips.add("Notez que les eaux gazeuses sont très bénéfiques, puisqu’elles permettent d’évacuer plus rapidement l’acidité présente dans votre corps à la suite d’un tel exercice physique.");



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
