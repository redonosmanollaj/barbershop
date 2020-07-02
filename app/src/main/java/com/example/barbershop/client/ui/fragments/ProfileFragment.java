package com.example.barbershop.client.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.barber.viewmodels.ServicesViewModel;
import com.example.barbershop.client.adapters.BarberServicesAdapter;
import com.example.barbershop.client.http.BarberServicesHttp;
import com.example.barbershop.client.ui.BarberInterface;
import com.example.barbershop.client.viewmodels.BarberServicesViewModel;
import com.example.barbershop.models.Barber;
import com.example.barbershop.models.Service;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements BarberInterface {


    private TextView tvBarberName, tvCity, tvCountry, tvStreet, tvBuilding, tvbarbershop;
    private Button btnBook;
    private String name;
    private String city, country, street, building, barbershop;
    private static int id;
    private RecyclerView recyclerView;
    private BarberServicesAdapter adapter;
    private BarberServicesViewModel viewModel;
    private List<Service> serviceList = new ArrayList<>();

    public ProfileFragment() {
        //blank constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_clinet_barber_profile, container, false);
        initializeVariables(v);

        adapter = new BarberServicesAdapter(serviceList);
        recyclerView = v.findViewById(R.id.barber_services_recyclerView);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(BarberServicesViewModel.class);
        viewModel.getData().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(List<Service> services) {
                setServices(services);
            }
        });

        getDataFromBundle();
        setTvValues();

        name = getBarberName();
        tvBarberName.setText(name);

        btnBook.setOnClickListener(e -> btnBook());

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void btnBook() {

    }

    private void initializeVariables(View v)
    {
        tvBarberName = v.findViewById(R.id.tv_fullname);
        tvCity = v.findViewById(R.id.tv_address_city);
        tvCountry = v.findViewById(R.id.tv_address_country);
        tvStreet = v.findViewById(R.id.tv_address_street);
        tvBuilding = v.findViewById(R.id.tv_address_building);
        tvbarbershop = v.findViewById(R.id.tv_barbershop_info);
        btnBook = v.findViewById(R.id.btn_book_from_barber_profile);
    }

    private void setServices(List<Service> services)
    {
        adapter.notifyDataSetChanged();
        for(Service service : services)
        {
            serviceList.add(service);
            adapter.notifyItemInserted(serviceList.indexOf(service));
        }
    }

    private void getDataFromBundle()
    {
        Bundle b = getArguments();
        city = b.getString("city");
        country = b.getString("country");
        street = b.getString("street");
        building = b.getString("building");
        barbershop = b.getString("barbershop");
        id = b.getInt("id");
    }

    private void setTvValues()
    {
        tvbarbershop.setText(barbershop);
        tvCity.setText(city);
        tvCountry.setText(country);
        tvStreet.setText("st." +street);
        tvBuilding.setText("Building: "+building);
    }
    private String getBarberName()
    {
        Bundle args = getArguments();
        assert args != null;
        return args.getString("barber_name");
    }

    @Override
    public int getBarberId() {
        return id != 0 ? id : 4;
    }


    @Override
    public String getName() {
        return null;
    }

}
