package com.pvjunkies.fieldcam;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    private String currentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!currentFragment.equals("home")) {
                        transaction.replace(R.id.fragment_container, new HomeFragment());

                        transaction.commit();
                        currentFragment = "home";
                    }

                    return true;
                case R.id.navigation_camera:
                    if (!currentFragment.equals("camera")) {
                        transaction.replace(R.id.fragment_container, new CameraFragment());

                        transaction.commit();
                        currentFragment = "camera";
                    }

                    return true;
                case R.id.navigation_settings:
                    if( !currentFragment.equals("settings") ) {
                        transaction.replace(R.id.fragment_container, new SettingsFragment());

                        transaction.commit();
                        currentFragment = "settings";
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if( findViewById(R.id.fragment_container) != null ) {
            if( savedInstanceState != null ) {
                return;
            }

            fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
            currentFragment = "home";
        }
    }
}
