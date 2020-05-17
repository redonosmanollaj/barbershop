package com.example.barbershop.barber.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.barbershop.R;
import com.example.barbershop.barber.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BarberActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new ProfileFragment()).commit();
        }

        setContentView(R.layout.activity_barber);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_profile: selectedFragment = new ProfileFragment();
                    break;
                    case R.id.nav_appointments: selectedFragment = new AppointmentsFragment();
                    break;
                    case R.id.nav_clients: selectedFragment = new ClientsFragment();
                    break;
                    case R.id.nav_notifications: selectedFragment = new NotificationsFragment();
                    break;
                    case R.id.nav_settings: selectedFragment = new SettingsFragment();
                    break;
                    default: break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                return true;
            }
        });


    }

}
