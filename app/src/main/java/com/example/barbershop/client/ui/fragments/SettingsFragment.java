package com.example.barbershop.client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.barbershop.R;

public class SettingsFragment extends Fragment {

    private TextView tvEditAcc, tvChangePassword, tvLogout;
    public SettingsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_client_settings, container, false);

        tvChangePassword = v.findViewById(R.id.tv_change_password_client);
        tvEditAcc = v.findViewById(R.id.tv_edit_account_client);
        tvLogout = v.findViewById(R.id.tv_logout_client);

        tvLogout.setOnClickListener(e -> logout());

        return v;
    }

    private void logout() {

    }
}
