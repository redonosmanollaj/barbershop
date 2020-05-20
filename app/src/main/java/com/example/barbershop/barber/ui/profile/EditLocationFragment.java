package com.example.barbershop.barber.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.barbershop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditLocationFragment extends Fragment {

    EditText etBarbershopName;
    TextView tvCancel;

    public EditLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_edit_location, container, false);

        etBarbershopName = root.findViewById(R.id.et_barbershop_name);
        etBarbershopName.requestFocus();

        tvCancel = root.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }
}
