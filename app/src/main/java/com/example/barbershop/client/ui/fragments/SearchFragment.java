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
import com.example.barbershop.client.ui.BarberInterface;
import com.example.barbershop.client.viewmodels.SearchedBarbersViewModel;
import com.example.barbershop.models.Barber;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SearchFragment extends Fragment implements SearchedBarbersAdapter.onBarberListener {
    public SearchFragment() {}

    private EditText etSearch;
    private ImageButton btnSearch;
    private TextView tvSearchedBarber, tvBlankList;
    private RecyclerView recyclerView;
    private SearchedBarbersViewModel viewModel;
    private SearchedBarbersAdapter searchedBarbersAdapter;
    private List<Barber> searchResultList = new ArrayList<>();
    private static String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_client_search, container, false);

        etSearch = view.findViewById(R.id.et_search_barbers);
        btnSearch = view.findViewById(R.id.btn_search_barbers);
        tvSearchedBarber = view.findViewById(R.id.tv_searched_barber);
        recyclerView = view.findViewById(R.id.search_barber_recyclerView);
        searchedBarbersAdapter = new SearchedBarbersAdapter(searchResultList, this);
        tvBlankList = view.findViewById(R.id.blank_list_notification);

        btnSearch.setOnClickListener(v -> displaySearchResult());

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

    private void displaySearchResult()
    {
        name = etSearch.getText().toString();
        setName(name);
        tvSearchedBarber.setText(name);
        if(name != "")
            tvSearchedBarber.setVisibility(View.VISIBLE);
        else
            tvSearchedBarber.setVisibility(View.INVISIBLE);
        etSearch.setText("");

        getLiveData();
    }

    private void clear() {
        searchResultList.clear();
        searchedBarbersAdapter.notifyDataSetChanged();
    }

    private void getLiveData()
    {
        try
        {
            viewModel = ViewModelProviders.of(this).get(SearchedBarbersViewModel.class);
            viewModel.getData().observe(this, (barbers)-> {
                setSearchResult(barbers);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setName(String name) { SearchFragment.name = name;}

    public static String GetName() {
        return name;
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

    @Override
    public void onBarberClick(int position)
    {
        String name = Objects.requireNonNull(viewModel.getData().getValue()).get(position).getName();
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString("barber_name", name);
        args.putString("barbershop", viewModel.getData().getValue().get(position).getBarbershop());
        args.putString("street", viewModel.getData().getValue().get(position).getStreet());
        args.putString("building", viewModel.getData().getValue().get(position).getBuilding());
        args.putString("city", viewModel.getData().getValue().get(position).getCity());
        args.putString("country", viewModel.getData().getValue().get(position).getCountry());
        args.putString("phone", viewModel.getData().getValue().get(position).getPhone());
        args.putInt("id", viewModel.getData().getValue().get(position).getId());

        profileFragment.setArguments(args);
        startActivity(profileFragment);
    }

}
