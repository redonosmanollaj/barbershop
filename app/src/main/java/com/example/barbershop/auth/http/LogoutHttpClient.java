package com.example.barbershop.auth.http;

import android.content.Context;

import com.example.barbershop.barber.http.BaseHttpClient;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogoutHttpClient extends BaseHttpClient {

    public static final String LOGOUT_PATH = "/api/logout";

    public LogoutHttpClient(Context c) {
        super(c);
    }

    public void logout(){
        Request request = new Request.Builder()
                .url(BASE_URL+LOGOUT_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
