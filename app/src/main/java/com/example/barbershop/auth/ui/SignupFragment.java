package com.example.barbershop.auth.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.barbershop.R;

public class SignupFragment extends Fragment {

    EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    Switch sIsBarber;
    Button btnSignup;
    private AwesomeValidation awesomeValidation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_signup, container, false);

        etName = root.findViewById(R.id.etName);
        etEmail = root.findViewById(R.id.etEmail);
        etPhone = root.findViewById(R.id.etPhone);
        etPassword = root.findViewById(R.id.etPassword);
        etConfirmPassword = root.findViewById(R.id.etConfirmPassword);
        sIsBarber = root.findViewById(R.id.isBarber);
        btnSignup = root.findViewById(R.id.btnSignup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(etName,"^([a-zA-Z0-9]+|[a-zA-Z0-9]+\\s{1}[a-zA-Z0-9]{1,}|[a-zA-Z0-9]+\\s{1}[a-zA-Z0-9]{3,}\\s{1}[a-zA-Z0-9]{1,})$",getString(R.string.name_error));
        awesomeValidation.addValidation(etEmail, Patterns.EMAIL_ADDRESS,getString(R.string.email_error));
        awesomeValidation.addValidation(etPhone,Patterns.PHONE,getString(R.string.phone_error));
        awesomeValidation.addValidation(etPassword,".{8,}",getString(R.string.password_error));
        //TODO: CONFIRM PASSWORD VALIDATION

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSignup();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void submitSignup(){
        if(awesomeValidation.validate()){
            Toast.makeText(getContext(),"Validation successful",Toast.LENGTH_LONG).show();
        }
    }
}
