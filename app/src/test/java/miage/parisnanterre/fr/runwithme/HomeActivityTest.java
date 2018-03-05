package miage.parisnanterre.fr.runwithme;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by nicolas on 03/03/18.
 */

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {
    public HomeActivityTest() {
        super(HomeActivity.class);
    }


    HomeActivity activite;
    Button boutonDemarrer;
    @Override
    protected void setUp() throws Exception{
        super.setUp();

        activite = (HomeActivity)getActivity();
        //boutonDemarrer = (Button)activite.findViewById(R.id.);
    }
}
