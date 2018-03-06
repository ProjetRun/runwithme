package miage.parisnanterre.fr.runwithme.application;

import org.junit.Test;

import miage.parisnanterre.fr.runwithme.RunningActivity;
import miage.parisnanterre.fr.runwithme.RunningStatistics;

import static org.junit.Assert.*;

import static junit.framework.Assert.assertEquals;

public class RunningActivityTest {

    @Test
    public void RA_meterDistanceBetweenPoints_isCorrect() throws Exception {
        RunningActivity activity = new RunningActivity();

        Double result = activity.meterDistanceBetweenPoints(1, 1,1,1);
        //assertEquals(result,);
    }
}
