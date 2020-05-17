package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.GlobalVariables;
import com.example.barbershop.auth.data.AuthPreference;

import okhttp3.OkHttpClient;

public class BaseHttpClient {

    protected final String BASE_URL = GlobalVariables.API_BASE_URL;

    protected OkHttpClient httpClient;
    protected String token;

    public BaseHttpClient(Context c){
        this.token = new AuthPreference(c).getToken();
        this.httpClient = new OkHttpClient();
    }



}
