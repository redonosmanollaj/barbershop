package com.example.barbershop.barber.ui.profile;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.barber.adapters.ServicesAdapter;
import com.example.barbershop.barber.viewmodels.ServicesViewModel;
import com.example.barbershop.models.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    ServicesViewModel viewModel;
    ServicesAdapter servicesAdapter;
    RecyclerView recyclerView;
    List<Service> serviceList = new ArrayList<>();
    TextView tvAddService;


    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_barber_profile_services, container, false);

        tvAddService = root.findViewById(R.id.tv_add_service);

        recyclerView = root.findViewById(R.id.services_recyclerview);
        servicesAdapter = new ServicesAdapter(serviceList);

        viewModel = ViewModelProviders.of(this).get(ServicesViewModel.class);
        viewModel.getData().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(List<Service> services) {
                setServices(services);
            }
        });

        tvAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new AddServiceFragment());
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(servicesAdapter);
    }

    private void setServices(List<Service> services){
        servicesAdapter.notifyDataSetChanged();
        for(Service service : services){
            serviceList.add(service);
            servicesAdapter.notifyItemInserted(services.indexOf(service));
        }
    }

    private void startFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .addToBackStack(null)
                .commit();
    }

}
