package miage.parisnanterre.fr.runwithme.application;
import org.junit.Test;

import miage.parisnanterre.fr.runwithme.MarathonTraining.Seance;
import miage.parisnanterre.fr.runwithme.badges.Badge;

import static junit.framework.Assert.assertEquals;

public class SeanceClassTest {

    @Test
    public void S_id_isCorrect() throws Exception {
        Seance seance = new Seance();
        seance.setId(1);
        assertEquals(seance.getId(),1);
    }
    @Test
    public void S_numsemaine_isCorrect() throws Exception {
        Seance seance = new Seance();
        seance.setNumSemaine(1);
        assertEquals(seance.getNumSemaine(),1);
    }
    @Test
    public void S_numseance_isCorrect() throws Exception {
        Seance seance = new Seance();
        seance.setNumSeance(1);
        assertEquals(seance.getNumSeance(),1);
    }
    @Test
    public void S_typeseance_isCorrect() throws Exception {
        Seance seance = new Seance();
        seance.setTypeSeance("test");
        assertEquals(seance.getTypeSeance(),"test");
    }

    @Test
    public void S_seance_isCorrect() throws Exception {
        Seance seance = new Seance();
        seance.setContenuSeance("test");
        assertEquals(seance.getContenuSeance(),"test");
    }


}
