package com.example.barbershop.client.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barbershop.R;
import com.example.barbershop.client.adapters.FavBarberAdapter;
import com.example.barbershop.client.adapters.SearchedBarbersAdapter;
import com.example.barbershop.client.viewmodels.MyBarbersViewModel;
import com.example.barbershop.models.Barber;
import com.example.barbershop.models.FavoriteBarbers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment
{
    TextView tvMyBarbers;
    TextView tvProfileLetter;
    Button findBarbers;
    private MutableLiveData<List<Barber>> data;

    MyBarbersViewModel viewModel;
    FavBarberAdapter myBarbersAdapter;
    RecyclerView recyclerView;
    String username;
    List<FavoriteBarbers> myBarbersList = new ArrayList<>();

    public HomeFragment() {
        //blank constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_client_home, container, false);

        findBarbers = view.findViewById(R.id.client_find_barbers);
        recyclerView = view.findViewById(R.id.barbers_recyclerview);
        tvMyBarbers = view.findViewById(R.id.fav_barber_name);
        myBarbersAdapter = new FavBarberAdapter(myBarbersList);
        tvProfileLetter = view.findViewById(R.id.profile_letter);

        viewModel = ViewModelProviders.of(this).get(MyBarbersViewModel.class);
        viewModel.getData().observe(this, new Observer<List<FavoriteBarbers>>() {
            @Override
            public void onChanged(List<FavoriteBarbers> favoriteBarbers) {
                if(favoriteBarbers != null) setFavoriteBarbers(favoriteBarbers);
            }
        });

        Bundle args = getArguments();

        if (args != null) username = args.getString("name");
        if (username != null) tvProfileLetter.setText(username.substring(0,1));

        findBarbers.setOnClickListener((e) -> {
            startActivity(new SearchFragment());
        });
        return view;
    }

    private void setFavoriteBarbers(List<FavoriteBarbers> favoriteBarbers)
    {
        myBarbersList.clear();
        myBarbersAdapter.notifyDataSetChanged();
        for (FavoriteBarbers favoriteBarber : favoriteBarbers)
        {
            myBarbersList.add(favoriteBarber);
            myBarbersAdapter.notifyItemInserted(myBarbersList.indexOf(favoriteBarber));
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myBarbersAdapter);
    }

    private void startActivity(Fragment fragment)
    {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_client_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }


}
