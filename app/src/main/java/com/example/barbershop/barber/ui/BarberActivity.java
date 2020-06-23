package com.example.barbershop.barber.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.barbershop.R;
import com.example.barbershop.auth.data.AuthPreference;
import com.example.barbershop.auth.ui.AuthActivity;
import com.example.barbershop.barber.ui.profile.EditLocationFragment;
import com.example.barbershop.barber.ui.profile.InfoFragment;
import com.example.barbershop.barber.ui.profile.ProfileFragment;
import com.example.barbershop.models.Location;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BarberActivity extends AppCompatActivity implements SettingsFragment.OnLogoutListener, EditLocationFragment.OnEditLocationListener {
    private BottomNavigationView bottomNavigation;
    private String userName;
    private AuthPreference authPreference;

    Fragment active;
    Fragment profileFragment = new ProfileFragment();
    Fragment appointmentsFragment;
    Fragment clientsFragment;
    Fragment notificationsFragment;
    Fragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        authPreference = new AuthPreference(this);

        active = profileFragment;
        final FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fm.beginTransaction().replace(R.id.nav_host_fragment,
                    profileFragment).commit();
        }

        Bundle bundle = new Bundle();
        bundle.putString("name",userName);
        profileFragment.setArguments(bundle);

        setContentView(R.layout.activity_barber);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_profile:
                        if(profileFragment == null) profileFragment = new ProfileFragment();
                        active = profileFragment;
                        break;
                    case R.id.nav_appointments:
                        if(appointmentsFragment == null) appointmentsFragment = new AppointmentsFragment();
                        active = appointmentsFragment;
                        break;
                    case R.id.nav_clients:
                        if(clientsFragment == null) clientsFragment = new ClientsFragment();
                        active = clientsFragment;
                        break;
                    case R.id.nav_notifications:
                        if(notificationsFragment == null) notificationsFragment = new NotificationsFragment();
                        active = notificationsFragment;
                        break;
                    case R.id.nav_settings:
                        if(settingsFragment == null) settingsFragment = new SettingsFragment();
                        active = settingsFragment;
                        break;
                    default:
                        return  false;
                }
                fm.beginTransaction().replace(R.id.nav_host_fragment,active).commit();
                return true;
            }
        });


    }

    @Override
    public void onLogout() {
        authPreference.setLoggedIn(false);
        authPreference.setToken(null);
        authPreference.setName(null);
        startAuthActivity();
        finish();
    }

    private void startAuthActivity(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void onEditLocation(Location location) {
        InfoFragment infoFragment = (InfoFragment)getSupportFragmentManager().findFragmentByTag();//TODO: HERE ...
        infoFragment.setLocation(location);
    }
}
