package com.example.barbershop.client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


    private TextView tvBarberName, tvCity, tvCountry, tvStreet, tvBuilding, tvbarbershop;
    private Button btnBook;
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
        tvBarberName = v.findViewById(R.id.tv_fullname);
        tvCity = v.findViewById(R.id.tv_address_city);
        tvCountry = v.findViewById(R.id.tv_address_country);
        tvStreet = v.findViewById(R.id.tv_address_street);
        tvBuilding = v.findViewById(R.id.tv_address_building);
        tvbarbershop = v.findViewById(R.id.tv_barbershop_info);
        btnBook = v.findViewById(R.id.btn_book_from_barber_profile);

        Bundle b = getArguments();
        city = b.getString("city");
        country = b.getString("country");
        street = b.getString("street");
        building = b.getString("building");
        barbershop = b.getString("barbershop");


        tvbarbershop.setText(barbershop);
        tvCity.setText(city);
        tvCountry.setText(country);
        tvStreet.setText("st." +street);
        tvBuilding.setText("Building: "+building);

        Bundle args = getArguments();
        assert args != null;
        name = args.getString("barber_name");
        tvBarberName.setText(name);

        btnBook.setOnClickListener(e -> btnBook());

        return v;
    }

    private void btnBook()
    {

    }
}
