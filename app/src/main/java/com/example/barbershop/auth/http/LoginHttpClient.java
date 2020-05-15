package com.example.barbershop.auth.http;

import com.example.barbershop.GlobalVariables;
import com.example.barbershop.R;
import com.example.barbershop.models.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginHttpClient {
    private String email, password;
    private OkHttpClient httpClient;
    private Gson gson;

    private static final String BASE_URL = GlobalVariables.API_BASE_URL;
    private static final String TOKEN_PATH = "/api/login";
    private static final String USER_DATA_PATH = "/api/user";
    private static final String DEVICE_NAME = "android";

    public LoginHttpClient(String email, String password) {
        this.email = email;
        this.password = password;
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    // return token or error
    public String getToken(){
        String token = null;
        RequestBody requestBody = new FormBody.Builder()
                .add("email",email)
                .add("password",password)
                .add("device_name",DEVICE_NAME)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL+TOKEN_PATH)
                .post(requestBody)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                String json = responseBody.string();
                JSONObject jsonObject = new JSONObject(json);
                if(jsonObject.has("plainTextToken"))
                    token = jsonObject.getString("plainTextToken");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;
    }


    public User getUser(String token){
        User user = null;
        Request request = new Request.Builder()
                .url(BASE_URL+USER_DATA_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null ){
                String json = responseBody.string();
                JSONObject jsonObject = new JSONObject(json);
                user = new User(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("email"),
                        jsonObject.getString("phone"),
                        jsonObject.getString("role"),
                        jsonObject.getString("avatar")
                );
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
