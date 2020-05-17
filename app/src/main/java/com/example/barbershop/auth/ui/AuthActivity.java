package com.example.barbershop.auth.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.barbershop.MainActivity;
import com.example.barbershop.R;
import com.example.barbershop.auth.data.AuthPreference;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity implements LoginFragment.OnLoginListener{

    TabLayout authTabLayout;
    ViewPager authViewPager;
    private AuthPreference authPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authPreference = new AuthPreference(this);
        authPreference.setLoggedIn(false);
        if(authPreference.isLoggedIn()){
            startBarberActivity();
            finish();
            return;
        }

        authViewPager = findViewById(R.id.auth_viewpager);
        authTabLayout = findViewById(R.id.tablayout_auth);

        setupViewPager();
        authTabLayout.setupWithViewPager(authViewPager);
    }

    private void setupViewPager(){
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new LoginFragment(),"LOGIN");
        tabAdapter.addFragment(new SignupFragment(),"SIGNUP");
        authViewPager.setAdapter(tabAdapter);
    }

    @Override
    public void onLogin(String token) {
        authPreference.setLoggedIn(true);
        authPreference.setToken(token);
        startBarberActivity();
        finish();
    }

    private void startBarberActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startClientActivity(){

    }


    static class TabAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> fragmentTitleList = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }
    }
}
