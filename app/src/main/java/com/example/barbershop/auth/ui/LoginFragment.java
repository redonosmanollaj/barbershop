package com.example.barbershop.auth.ui;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.R;
import com.example.barbershop.auth.data.AuthPreference;
import com.example.barbershop.auth.http.LoginHttpClient;
import com.example.barbershop.models.User;

public class LoginFragment extends Fragment {

    public EditText etEmail, etPassword;
    public TextView tvError;
    public Button btnLogin;
    public ProgressBar loadingProgressBar;


    public interface OnLoginListener{
        void onLogin(String token, User user);
    }

    private OnLoginListener loginListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_login, container, false);

//        authPreference = new AuthPreference(getContext());
//        if(authPreference.isLoggedIn()){
//            startMainActivity();
//            finish();
//        }

        etEmail = root.findViewById(R.id.etEmail);
        etPassword = root.findViewById(R.id.etPassword);
        tvError = root.findViewById(R.id.tvError);
        btnLogin = root.findViewById(R.id.btnLogin);
        loadingProgressBar = root.findViewById(R.id.loading_progressbar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new LoginAsyncTask().execute();
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnLoginListener){
            loginListener =  (OnLoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginListener = null;
    }

    private void disableForm(){
        etEmail.setEnabled(false);
        etEmail.setAlpha((float) 0.4);
        etPassword.setEnabled(false);
        etPassword.setAlpha((float)0.4);
        btnLogin.setEnabled(false);
        btnLogin.setAlpha((float)0.4);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void enableForm(){
        etEmail.setEnabled(true);
        etEmail.setAlpha(1);
        etPassword.setEnabled(true);
        etPassword.setAlpha(1);
        btnLogin.setEnabled(true);
        btnLogin.setAlpha(1);
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }


    class LoginAsyncTask extends AsyncTask<Void,Void,User>{
        private String email, password;

        @Override
        protected void onPreExecute() {
            this.email = etEmail.getText().toString();
            this.password = etPassword.getText().toString();
            disableForm();
        }

        @Override
        protected User doInBackground(Void... voids) {
            LoginHttpClient httpClient = new LoginHttpClient(email,password);
            String token = httpClient.getToken();

            User user = null;
            if(token != null){
                user = httpClient.getUser(token);
                if(loginListener != null)
                    loginListener.onLogin(token,user);
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            if(user != null){
                tvError.setText("");
                Toast.makeText(getContext(),"Welcome, "+user.getName(),Toast.LENGTH_LONG).show();
            } else {
                tvError.setText(R.string.incorrect_credentials);
                enableForm();
            }

        }
    }
}
