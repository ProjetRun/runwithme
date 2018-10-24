package miage.parisnanterre.fr.runwithme.tab;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.bottomNavigation.ChallengesFragment;
import miage.parisnanterre.fr.runwithme.bottomNavigation.GroupFragment;
import miage.parisnanterre.fr.runwithme.bottomNavigation.HomeFragment;
import miage.parisnanterre.fr.runwithme.bottomNavigation.ProfilFragment;
import miage.parisnanterre.fr.runwithme.bottomNavigation.TrainingFragment;

public class DashboardActivity extends AppCompatActivity {

    Fragment currentFragment = null;
    FragmentTransaction ft;
    BottomNavigationView bottomNavigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    position = 0;
                    currentFragment = new HomeFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
                case R.id.training:
                    position = 1;
                    currentFragment = new TrainingFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
                case R.id.group:
                    position=2;
                    currentFragment = new GroupFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
                case R.id.defis:
                    position= 3;
                    currentFragment = new ChallengesFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
                case R.id.profil:
                    position=4;
                    currentFragment = new ProfilFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    private boolean simulateClick(){

            switch (position) {
                case 0:
                    bottomNavigation.setSelectedItemId(R.id.home);
                    return true;
                case 1:
                    //bottomNavigation.getMenu().findItem(R.id.training);
                    bottomNavigation.setSelectedItemId(R.id.training);
                case 2:
                    bottomNavigation.setSelectedItemId(R.id.group);
                    return true;
                case 3:
                    bottomNavigation.setSelectedItemId(R.id.defis);
                    return true;
                case 4:
                    bottomNavigation.setSelectedItemId(R.id.profil);
                    return true;
            }
            return false;

    }

    private float x1,x2;
    private int position = 0;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        position = position == 0 ? position : position -1;
                        //position--;
                        Toast.makeText(this, "Right to Left swipe [Previous] : ", Toast.LENGTH_SHORT).show ();
                        this.simulateClick();
                    }

                    // Right to left swipe action
                    else
                    {
                        position = position == 5 ? position : position+1;
                        //position++;
                        Toast.makeText(this, "Left to Right swipe [Next] : ", Toast.LENGTH_SHORT).show ();
                        this.simulateClick();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new HomeFragment();
        ft.replace(R.id.container, currentFragment);
        ft.commit();


        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}