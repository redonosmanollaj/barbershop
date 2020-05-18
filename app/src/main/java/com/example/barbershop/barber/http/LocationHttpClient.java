package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.GlobalVariables;
import com.example.barbershop.models.Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LocationHttpClient extends BaseHttpClient{

    private String barbershop;
    private String street;
    private String building;
    private String city;
    private String country;

    private static final String LOCATION_PATH = "/api/location";

    public LocationHttpClient(Context c) {
        super(c);
    }

    public LocationHttpClient(Context c, String barbershop, String street, String building, String city, String country) {
        super(c);
        this.barbershop = barbershop;
        this.street = street;
        this.building = building;
        this.city = city;
        this.country = country;
    }

    public Location getLocation(){
        Location location = null;

        Request request = new Request.Builder()
                .url(BASE_URL+LOCATION_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient
                    .newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                String json = responseBody.string();
                JSONObject jsonObject = new JSONObject(json);
                location = new Location(jsonObject.getString("barbershop"),
                                        jsonObject.getString("street"),
                                        jsonObject.getString("building"),
                                        jsonObject.getString("city"),
                                        jsonObject.getString("country"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }

    public void storeLocation(){
        //TODO: insert location with POST
    }

    public void editLocation(){
        //TODO: update location with PUT
    }


}
