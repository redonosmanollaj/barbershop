package com.example.barbershop.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.barbershop.R;
import com.example.barbershop.client.ui.fragments.AppointmentFragment;
import com.example.barbershop.client.ui.fragments.HomeFragment;
import com.example.barbershop.client.ui.fragments.SearchFragment;
import com.example.barbershop.client.ui.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientActivity extends AppCompatActivity {

    private BottomNavigationView clientBottomNavigation;
    private String username;
    Fragment homeFragment = new HomeFragment();
    Fragment appointmentFragment;
    Fragment searchFragment;
    Fragment settingsFragment;
    Fragment active;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        username = intent.getStringExtra("name");

        final FragmentManager fm = getSupportFragmentManager(); // suggested by ide to be final

            fm.beginTransaction().replace(R.id.nav_client_fragment,
                    homeFragment).commit();

        Bundle bundle = new Bundle();
        bundle.putString("name", username);
        homeFragment.setArguments(bundle);
       // appointmentFragment.setArguments(bundle);

        setContentView(R.layout.activity_client);

        clientBottomNavigation = findViewById(R.id.client_bottom_navigation);
        clientBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.appointment_navbar:
                        if (appointmentFragment == null) appointmentFragment = new AppointmentFragment();
                        active = appointmentFragment;
                        break;
                    case R.id.search_navbar:
                        if (searchFragment == null) searchFragment = new SearchFragment();
                        active = searchFragment;
                        break;
                    case R.id.setting_navbar:
                        if (settingsFragment == null) settingsFragment = new SettingsFragment();
                        active = settingsFragment;
                        break;
                    case R.id.home_navbar:
                        if (homeFragment == null) homeFragment = new HomeFragment();
                        active = homeFragment;
                        break;
                    default:
                        return false;
                }
                fm.beginTransaction().replace(R.id.nav_client_fragment, active).commit();
                return true;
            }
        });
    }
}
