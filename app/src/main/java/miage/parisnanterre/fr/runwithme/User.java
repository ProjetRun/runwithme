package miage.parisnanterre.fr.runwithme;

/**
 * Created by MICHEL Fabien on 13/03/2018.
 */

public class User {

    int id;
    int level;
    int km;

    public User() {

        this.id = 0;
        this.level = 1;
        this.km=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
    public void updateKm(int u){
        km+=u;
        if(km>(60*level)){
            level++;
            km=0;
        }

    }
    public int getkmNextLevel(){
        return level*60;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", level=" + level +
                ", km=" + km +
                '}';
    }
}
