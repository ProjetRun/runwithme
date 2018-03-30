package miage.parisnanterre.fr.runwithme.application;

import org.junit.Test;

import miage.parisnanterre.fr.runwithme.User;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by nicolas on 28/03/18.
 */
public class UserTest {
    @Test
    public void userId() throws Exception {
        User user = new User();
        user.setId(01);
        assertEquals(user.getId(), 01);
    }

    @Test
    public void userLevel() throws Exception {
        User user = new User();
        user.setLevel(10);
        assertEquals(user.getLevel(), 10);
    }

    @Test
    public void userKm() throws Exception {
        User user = new User();
        user.setLevel(30);
        assertEquals(user.getLevel(), 30);
    }

    @Test
    public void userUpdateKm() throws Exception {
        User user = new User();
        user.setKm(5);
        user.updateKm(10);
        assertEquals(user.getKm(), 15);
    }

    @Test
    public void userNextLevel() throws Exception {
        User user = new User();
        user.setLevel(1);
        user.setLevel(user.getkmNextLevel());
        assertEquals(user.getLevel(), 20);
    }

}