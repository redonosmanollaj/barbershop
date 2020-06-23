package com.example.barbershop.barber.ui.profile;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.auth.ui.SignupFragment;
import com.example.barbershop.barber.http.LocationHttpClient;
import com.example.barbershop.models.Location;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditLocationFragment extends Fragment {

    EditText etBarbershopName, etStreet, etBuilding, etCity, etCountry;
    TextView tvCancel, tvSave;

    public EditLocationFragment() {
        // Required empty public constructor
    }

    public interface OnEditLocationListener{
        void onEditLocation(Location location);
    }

    private EditLocationFragment.OnEditLocationListener editLocationListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnEditLocationListener){
            editLocationListener =  (OnEditLocationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnEditLocationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        editLocationListener = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_edit_location, container, false);

        Bundle arguments = getArguments();


        etBarbershopName = root.findViewById(R.id.et_barbershop_name);
        etStreet = root.findViewById(R.id.et_street);
        etBuilding = root.findViewById(R.id.et_building);
        etCity = root.findViewById(R.id.et_city);
        etCountry = root.findViewById(R.id.et_country);
        etBarbershopName.requestFocus();

        if(arguments != null){
            etBarbershopName.setText(arguments.getString("barbershop"));
            etStreet.setText(arguments.getString("street"));
            etBuilding.setText(arguments.getString("building"));
            etCity.setText(arguments.getString("city"));
            etCountry.setText(arguments.getString("country"));
        }

        tvCancel = root.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        tvSave = root.findViewById(R.id.tvSave);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditLocationAsyncTask().execute();
            }
        });

        return root;
    }

    class EditLocationAsyncTask extends AsyncTask<Void,Void, Location>{

        private String barbershop, street, building, city, country;

        @Override
        protected void onPreExecute() {
            this.barbershop = etBarbershopName.getText().toString();
            this.street = etStreet.getText().toString();
            this.building = etBuilding.getText().toString();
            this.city = etCity.getText().toString();
            this.country = etCountry.getText().toString();
        }

        @Override
        protected Location doInBackground(Void... voids) {
            Location location;
            LocationHttpClient httpClient = new LocationHttpClient(getContext(),barbershop,street,building,city,country);
            location = httpClient.editLocation();
            return location;
        }

        @Override
        protected void onPostExecute(Location location) {
            if(editLocationListener != null){
                editLocationListener.onEditLocation(location);
            }
            getActivity().onBackPressed();
        }
    }
}
