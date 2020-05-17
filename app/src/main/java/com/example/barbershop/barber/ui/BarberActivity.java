package com.example.barbershop.barber.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.barbershop.R;
import com.example.barbershop.barber.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BarberActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private String userName;
    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        final Fragment profileFragment = new ProfileFragment();
        final Fragment appointmentsFragment = new AppointmentsFragment();
        final Fragment clientsFragment = new ClientsFragment();
        final Fragment notificationsFragment = new NotificationsFragment();
        final Fragment settingsFragment = new SettingsFragment();
        final FragmentManager fm = getSupportFragmentManager();
        active = profileFragment;

        Bundle bundle = new Bundle();
        bundle.putString("name",userName);
        profileFragment.setArguments(bundle);
        
        setContentView(R.layout.activity_barber);

        fm.beginTransaction().add(R.id.nav_host_fragment,appointmentsFragment).hide(appointmentsFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,clientsFragment).hide(clientsFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,notificationsFragment).hide(notificationsFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,settingsFragment).hide(settingsFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,profileFragment).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_profile:
                        fm.beginTransaction().hide(active).show(profileFragment).commit();
                        active = profileFragment;
                        return true;
                    case R.id.nav_appointments:
                        fm.beginTransaction().hide(active).show(appointmentsFragment).commit();
                        active = appointmentsFragment;
                        return true;
                    case R.id.nav_clients:
                        fm.beginTransaction().hide(active).show(clientsFragment).commit();
                        active = clientsFragment;
                        return true;
                    case R.id.nav_notifications:
                        fm.beginTransaction().hide(active).show(notificationsFragment).commit();
                        active = notificationsFragment;
                        return true;
                    case R.id.nav_settings:
                        fm.beginTransaction().hide(active).show(settingsFragment).commit();
                        active = settingsFragment;
                        return true;
                }
                return false;
            }
        });


    }

}
