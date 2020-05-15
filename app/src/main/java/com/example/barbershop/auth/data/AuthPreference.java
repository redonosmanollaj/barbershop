package com.example.barbershop.auth.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.barbershop.models.User;

public class AuthPreference {
    private static final String LOGIN_STATE = "login_state";
    private static final String TOKEN = "token";

    private SharedPreferences preferences;

    public AuthPreference(Context context){
        preferences = context.getSharedPreferences("barbershop",Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(LOGIN_STATE,false);
    }

    public void setLoggedIn(boolean loggedIn){
        preferences.edit().putBoolean(LOGIN_STATE,loggedIn).apply();
    }

    public String getToken(){
        return preferences.getString(TOKEN,null);
    }

    public void setToken(String token){
        preferences.edit().putString(TOKEN,token).apply();
    }

}
