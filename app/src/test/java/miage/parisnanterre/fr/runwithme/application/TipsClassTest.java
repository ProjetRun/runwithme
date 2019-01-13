package miage.parisnanterre.fr.runwithme.application;
import org.junit.Test;

import miage.parisnanterre.fr.runwithme.badges.Badge;
import miage.parisnanterre.fr.runwithme.bottomNavigation.Tips;

import static junit.framework.Assert.assertEquals;

public class TipsClassTest {

    @Test
    public void T_compteur_isCorrect() throws Exception {
        Tips tip = new Tips();
        tip.setCompteur(1);
        assertEquals(tip.getCompteur(),1);
    }


}
