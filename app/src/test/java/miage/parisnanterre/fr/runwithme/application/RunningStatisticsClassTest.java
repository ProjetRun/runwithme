package miage.parisnanterre.fr.runwithme.application;
import org.junit.Test;

import miage.parisnanterre.fr.runwithme.running.RunningStatistics;

import static junit.framework.Assert.assertEquals;

public class RunningStatisticsClassTest {

    @Test
    public void RS_date_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setDate("02/02/02");
        assertEquals(rs.getDate(), "02/02/02");
    }
    @Test
    public void RS_heure_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setHeure("16h");
        assertEquals(rs.getHeure(), "16h");
    }
    @Test
    public void RS_distance_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setDistance("8km");
        assertEquals(rs.getDistance(), "8km");
    }
    @Test
    public void RS_duree_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setDuree("48minutes");
        assertEquals(rs.getDuree(), "48minutes");
    }
    @Test
    public void RS_rythme_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setRythme("10km/h");
        assertEquals(rs.getRythme(), "10km/h");
    }
    @Test
    public void RS_calories_isCorrect() throws Exception {
        RunningStatistics rs = new RunningStatistics("01/01/01","15H02","3","45minutes","8km/h","18");
        rs.setCalories("20");
        assertEquals(rs.getCalories(), "20");
    }
}
