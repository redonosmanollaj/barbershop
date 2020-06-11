package com.example.barbershop.barber.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.barber.adapters.ClientsAdapter;
import com.example.barbershop.barber.viewmodels.ClientsViewModel;
import com.example.barbershop.models.Client;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsFragment extends Fragment {

    private ClientsViewModel viewModel;
    private ClientsAdapter adapter;
    private RecyclerView recyclerView;
    private List<Client> clientList = new ArrayList<>();
    private TextView tvNoClients;


    public ClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_barber_clients, container, false);

        recyclerView = root.findViewById(R.id.clients_recyclerview);
        tvNoClients = root.findViewById(R.id.tv_no_clients);
        adapter = new ClientsAdapter(clientList);

        viewModel = ViewModelProviders.of(this).get(ClientsViewModel.class);
        viewModel.getData().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                if(clients != null){
                    setClients(clients);
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setClients(List<Client> clients){
        clientList.clear();
        adapter.notifyDataSetChanged();
        if(clients.size() > 0){
            tvNoClients.setVisibility(View.INVISIBLE);
            for (Client client : clients){
                clientList.add(client);
                adapter.notifyItemInserted(clientList.indexOf(client));
            }
        } else {
            tvNoClients.setVisibility(View.VISIBLE);
        }
    }

}
