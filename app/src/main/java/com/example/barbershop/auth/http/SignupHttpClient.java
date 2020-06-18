package com.example.barbershop.auth.http;

import com.example.barbershop.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SignupHttpClient {
    private String name, email, phone,password,password_confirmation,role;
    private boolean is_barber;
    private OkHttpClient httpClient;

    private static final String BASE_URL = GlobalVariables.API_BASE_URL;
    private static final String SIGNUP_PATH = "/api/register";
    private static final String DEVICE_NAME = "android";

    public SignupHttpClient(String name, String email, String phone,String password, String password_confirmation, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.role = role;
        this.httpClient = new OkHttpClient();
    }

    // return: token
    public String signup(){
        String token = null;
        RequestBody requestBody = new FormBody.Builder()
                .add("name",name)
                .add("email",email)
                .add("phone",phone)
                .add("password",password)
                .add("password_confirmation",password_confirmation)
                .add("role",role)
                .add("device_name",DEVICE_NAME)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL+SIGNUP_PATH)
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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
