package com.example.barbershop.client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.barbershop.R;
import com.example.barbershop.client.adapters.ProfileBarberSectionPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private TextView etBarberName;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private String name;
    private String city, country, street, building, barbershop;

    private ProfileBarberSectionPagerAdapter adapter;

    public ProfileFragment() {
        //blank constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_clinet_barber_profile, container, false);
        etBarberName = v.findViewById(R.id.tv_fullname);
        tabLayout = v.findViewById(R.id.tablayout_profile_);
        appBarLayout = v.findViewById(R.id.appBarLayout);
        viewPager = v.findViewById(R.id.profile_viewpager_);
        adapter = new ProfileBarberSectionPagerAdapter(getChildFragmentManager());

        Bundle b = getArguments();
        city = b.getString("city");
        country = b.getString("country");
        street = b.getString("street");
        building = b.getString("building");
        barbershop = b.getString("barbershop");

        Bundle bundle = new Bundle();
        InfoFragment infoFragment = new InfoFragment();
        bundle.putString("city", city);
        bundle.putString("country", country);
        bundle.putString("street", street);
        bundle.putString("building", building);
        bundle.putString("barbershop", barbershop);
        infoFragment.setArguments(bundle);
        startActivity(infoFragment);

        adapter.addFragment(new InfoFragment(), "Info");
        adapter.addFragment(new ReviewFragment(), "Reviews");
        adapter.addFragment(new ServicesFragment(), "Services");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle args = getArguments();
        assert args != null;
        name = args.getString("barber_name");
        etBarberName.setText(name);

        return v;
    }

    private void putData() {

    }

    private void getData() {


    }

    private void startActivity(Fragment fragment)
    {
        try {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_client_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
