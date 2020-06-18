package com.example.barbershop.client.ui.fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.GlobalVariables;
import com.example.barbershop.R;
import com.example.barbershop.auth.data.AuthPreference;
import com.example.barbershop.barber.http.BaseHttpClient;
import com.example.barbershop.client.adapters.FavBarberAdapter;
import com.example.barbershop.models.FavoriteBarbers;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment
{
    private ArrayList<FavoriteBarbers> favoriteBarbers;
    private FavBarberAdapter barberAdapter;
    private Button findBarbers;
    private Request request;
    private OkHttpClient okHttpClient;
    private RecyclerView recyclerView;

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
        okHttpClient = new OkHttpClient();
        favoriteBarbers = new ArrayList<>();

//        try
//        {
//            request = new Request.Builder().
//                    url(myBarbersURL).
//                    addHeader("token", "")
//                    .build();
//            okHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                    Log.i("fail", e.getMessage());
//                }
//
//                @Override
//                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    Log.i("data", response.body().string());
//                }
//            });
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        String[] myBarbers = {"Filani", "Fisteku", "Filani", "Fisteku","asdads","asdasd","asdasd"};
        favoriteBarbers = new ArrayList<>();
        for(int i=0; i<myBarbers.length; i++)
        {
            favoriteBarbers.add(new FavoriteBarbers(myBarbers[i]));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        barberAdapter = new FavBarberAdapter(getActivity(), favoriteBarbers);
        recyclerView.setAdapter(barberAdapter);


        findBarbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new SearchFragment());
            }
        });
        return view;
    }

    private void startActivity(Fragment fragment)
    {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_client_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }


}
