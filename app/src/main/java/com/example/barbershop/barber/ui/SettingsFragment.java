package com.example.barbershop.barber.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.auth.data.AuthPreference;
import com.example.barbershop.auth.http.LogoutHttpClient;

public class SettingsFragment extends Fragment {

    TextView tvEditAccount, tvChangePassword, tvLogout;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public interface OnLogoutListener{
        void onLogout();
    }

    private OnLogoutListener logoutListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnLogoutListener){
            logoutListener =  (OnLogoutListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logoutListener = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_barber_settings, container, false);

        tvEditAccount = root.findViewById(R.id.tv_edit_account);
        tvChangePassword = root.findViewById(R.id.tv_change_password);
        tvLogout = root.findViewById(R.id.tv_logout);

        tvEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });



        return root;
    }

    private void logout(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                LogoutHttpClient httpClient = new LogoutHttpClient(getContext());
                httpClient.logout();
                return null;
            }
        }.execute();

        if(logoutListener != null)
            logoutListener.onLogout();
    }

}
