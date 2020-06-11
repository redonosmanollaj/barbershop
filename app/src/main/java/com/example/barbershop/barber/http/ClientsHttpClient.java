package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.models.Client;
import com.example.barbershop.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ClientsHttpClient extends BaseHttpClient {

    public static final String CLIENTS_PATH = "/api/clients";

    public ClientsHttpClient(Context c) {
        super(c);
    }

    public List<Client> getClients(){
        List<Client> clients = new ArrayList<>();

        Request request = new Request.Builder()
                .url(BASE_URL+CLIENTS_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if(responseBody != null){
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Client client = new Client(
                        jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("avatar")
                    );
                    clients.add(client);
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
