package miage.parisnanterre.fr.runwithme.Challenges;


public class Questions {

    private String question;
    private Boolean reponse;
    private String url;
    private boolean appeared;

    public Questions(String question, Boolean reponse, String url) {
        this.question = question;
        this.reponse = reponse;
        this.url = url;
        this.appeared = false;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getAppeared() {
        return appeared;
    }

    public void setAppeared(boolean appeared) {
        this.appeared = appeared;
    }
}
