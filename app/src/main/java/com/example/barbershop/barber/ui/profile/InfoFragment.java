package com.example.barbershop.barber.ui.profile;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.barber.http.HoursHttpClient;
import com.example.barbershop.barber.http.LocationHttpClient;
import com.example.barbershop.barber.viewmodels.InfoViewModel;
import com.example.barbershop.models.Hours;
import com.example.barbershop.models.Info;
import com.example.barbershop.models.Location;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    private TextView tvBarbershop, tvStreet, tvBuilding, tvCity, tvCountry;
    private InfoViewModel viewModel;
    private LinearLayout hoursLayout;
    private TextView tvEditLocation, tvEditHours, tvAddPhoto;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_barber_profile_info,container,false);

        tvBarbershop = root.findViewById(R.id.tv_barbershop);
        tvStreet = root.findViewById(R.id.tv_street);
        tvBuilding = root.findViewById(R.id.tv_building);
        tvCity = root.findViewById(R.id.tv_city);
        tvCountry = root.findViewById(R.id.tv_country);
        tvEditLocation = root.findViewById(R.id.tv_edit_location);

        hoursLayout = (LinearLayout)root.findViewById(R.id.linear_layout_hours);

        viewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        viewModel.getData().observe(this, new Observer<Info>() {
            @Override
            public void onChanged(Info info) {
                setInfo(info);
            }
        });

        tvEditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new EditLocationFragment());
            }
        });

        return root;
    }

    private void setLocation(Location location){
        tvBarbershop.setText(location.getBarbershop());
        tvStreet.setText(location.getStreet());
        tvBuilding.setText(location.getBuilding());
        tvCity.setText(location.getCity());
        tvCountry.setText(location.getCountry());
    }

    private void setHours(List<Hours> hours){
        TextView tvDay,tvWorkingHours,tvBreakHours;
        for(int i=0;i<hours.size();i++){
            Hours hoursObj = hours.get(i);
            View hoursView = getLayoutInflater().inflate(R.layout.item_hours,null);

            tvDay = hoursView.findViewById(R.id.item_hours_day);
            tvWorkingHours = hoursView.findViewById(R.id.item_working_hours);
            tvBreakHours = hoursView.findViewById(R.id.item_break_hours);

            tvDay.setText(hoursObj.getDay());
            tvWorkingHours.setText(hoursObj.getWorkingHours());
            tvBreakHours.setText(hoursObj.getBreakHours());
            hoursLayout.addView(hoursView);
        }
    }

    private void setInfo(Info info){
        if(info != null){
            setLocation(info.getLocation());
            setHours(info.getHours());
        }
    }

    private void startFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .addToBackStack(null)
                .commit();
    }

}
