package miage.parisnanterre.fr.runwithme.Challenges;


public class Questions {

    private String question;
    private Boolean reponse;

    public Questions(String question, Boolean reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getReponse() {
        return reponse;
    }

    public void setReponse(Boolean reponse) {
        this.reponse = reponse;
    }
}
