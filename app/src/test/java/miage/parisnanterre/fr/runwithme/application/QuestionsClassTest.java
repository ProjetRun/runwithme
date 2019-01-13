package miage.parisnanterre.fr.runwithme.application;
import org.junit.Test;

import miage.parisnanterre.fr.runwithme.Challenges.Questions;
import miage.parisnanterre.fr.runwithme.running.RunningStatistics;

import static junit.framework.Assert.assertEquals;

public class QuestionsClassTest {

    @Test
    public void Q_question_isCorrect() throws Exception {
        Questions question = new Questions("question ?", true,"https://codecov.io");
        question.setQuestion("une autre question?");
        assertEquals(question.getQuestion(), "une autre question?");
    }

    @Test
    public void Q_reponse_isCorrect() throws Exception {
        Questions question = new Questions("question ?", true,"https://codecov.io");
        question.setReponse(true);
        assertEquals(question.getQuestion(), "question ?");
    }


    @Test
    public void Q_url_isCorrect() throws Exception {
        Questions question = new Questions("question ?", true,"https://codecov.io");
        question.setUrl("www.google.fr");
        assertEquals(question.getUrl(),"www.google.fr");
    }
}
