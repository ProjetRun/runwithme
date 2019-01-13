package miage.parisnanterre.fr.runwithme.application;
import org.junit.Test;

import miage.parisnanterre.fr.runwithme.Challenges.Questions;
import miage.parisnanterre.fr.runwithme.badges.Badge;

import static junit.framework.Assert.assertEquals;

public class BadgeClassTest {

    @Test
    public void B_numero_isCorrect() throws Exception {
        Badge badge = new Badge(1,"test");
        badge.setNumero(2);
        assertEquals(badge.getNumero(),2);
    }

    @Test
    public void B_nom_isCorrect() throws Exception {
        Badge badge = new Badge(1,"test");
        badge.setNom("badge");
        assertEquals(badge.getNom(),"badge");
    }

}
