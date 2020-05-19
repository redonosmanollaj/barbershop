package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.models.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ServicesHttpClient extends BaseHttpClient{

    private static final String SERVICE_PATH = "/api/services";

    public ServicesHttpClient(Context c) {
        super(c);
    }

    public List<Service> getServices(){
        List<Service> services = new ArrayList<>();

        Request request = new Request.Builder()
                .url(BASE_URL+SERVICE_PATH)
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
                    Service service = new Service(
                            jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getString("price"),
                            jsonObject.getInt("duration"),
                            jsonObject.getString("description")
                    );
                    services.add(service);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return services;
    }
}
