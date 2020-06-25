package com.example.barbershop.client.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barbershop.R;
import com.example.barbershop.client.adapters.SearchedBarbersAdapter;
import com.example.barbershop.client.viewmodels.SearchedBarbersViewModel;
import com.example.barbershop.models.Barber;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    public SearchFragment() {}

    private EditText etSearch;
    private ImageButton btnSearch;
    private TextView tvSearchedBarber;
    private RecyclerView recyclerView;
//    SearchFragment s = new SearchFragment();
//    private SearchFragment.SearchedBarbersViewModel viewModel = s.new SearchedBarbersViewModel();
    private SearchedBarbersViewModel viewModel;
//    private MutableLiveData<List<Barber>> data;
//    MutableLiveData searchBarbersAsyncTaskobj;

    private static String name;
    private SearchedBarbersAdapter searchedBarbersAdapter;
    private List<Barber> searchResultList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_client_search, container, false);

        etSearch = view.findViewById(R.id.et_search_barbers);
        btnSearch = view.findViewById(R.id.btn_search_barbers);
        tvSearchedBarber = view.findViewById(R.id.tv_searched_barber);
        recyclerView = view.findViewById(R.id.search_barber_recyclerView);
        searchedBarbersAdapter = new SearchedBarbersAdapter(searchResultList);

        btnSearch.setOnClickListener(v -> btnSearchClicked());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(searchedBarbersAdapter);
    }

    private void setSearchResult(List<Barber> results) {
        searchResultList.clear();
        searchedBarbersAdapter.notifyDataSetChanged();
        for(Barber result : results)
        {
            searchResultList.add(result);
            searchedBarbersAdapter.notifyItemInserted(searchResultList.indexOf(result));
        }
    }

    private void btnSearchClicked()
    {
        setName(getName());
        tvSearchedBarber.setText(name);
        tvSearchedBarber.setVisibility(View.VISIBLE);
        etSearch.setText("");

        getLiveData();
    }

    private void getLiveData()
    {
        try {
            viewModel = ViewModelProviders.of(this).get(SearchedBarbersViewModel.class);
            viewModel.getData().observe(this, (barbers)-> {
                if(barbers != null) setSearchResult(barbers);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getName() { return this.etSearch.getText().toString(); }
    public void setName(String name) { SearchFragment.name = name;}

    public static String GetName() {
        return name;
    }

}
