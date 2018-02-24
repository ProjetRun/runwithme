package miage.parisnanterre.fr.runwithme;

public class RunningStatistics {

    String date;
    String heure;
    String distance;
    String duree;
    String rythme;
    String calories;

    public RunningStatistics(String date, String heure,String distance, String duree, String rythme, String calories) {
        this.date = date;
        this.heure = heure;
        this.distance = distance;
        this.duree = duree;
        this.rythme = rythme;
        this.calories = calories;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getRythme() {
        return rythme;
    }

    public void setRythme(String rythme) {
        this.rythme = rythme;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
