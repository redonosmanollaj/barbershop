package com.example.barbershop.client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.barbershop.R;

public class InfoFragment extends Fragment {
    private View v;

    TextView tvCity, tvCountry, tvStreet, tvBuilding, tvbarbershop;
    String city, country, street, building, barbershop;

    public InfoFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_client_profile_info, container, false);
        tvCity = v.findViewById(R.id.tv_address_city);
        tvCountry = v.findViewById(R.id.tv_address_country);
        tvStreet = v.findViewById(R.id.tv_address_street);
        tvBuilding = v.findViewById(R.id.tv_address_building);
        tvbarbershop = v.findViewById(R.id.tv_barbershop_info);

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

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
