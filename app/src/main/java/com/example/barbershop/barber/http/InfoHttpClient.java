package com.example.barbershop.barber.http;

import android.content.Context;
import android.service.voice.VoiceInteractionSession;

import com.example.barbershop.models.Hours;
import com.example.barbershop.models.Info;
import com.example.barbershop.models.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InfoHttpClient extends BaseHttpClient{
    private static final String INFO_PATH = "/api/info";

    public InfoHttpClient(Context c) {
        super(c);
    }

    public Info getInfo(){
        Info info = null;

        Request request = new Request.Builder()
                .url(BASE_URL+INFO_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                String json = responseBody.string();
                JSONObject jsonObject = new JSONObject(json);

                Location location = getLocationFromJson(jsonObject.getJSONObject("location"));
                List<Hours> hoursList = getHoursFromJson(jsonObject.getJSONArray("hours"));

                info = new Info(location,hoursList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info;
    }

    private Location getLocationFromJson(JSONObject jsonObject) throws JSONException {

        Location location = new Location(jsonObject.getString("barbershop"),
                jsonObject.getString("street"),
                jsonObject.getString("building"),
                jsonObject.getString("city"),
                jsonObject.getString("country"));
        return location;
    }

    private List<Hours> getHoursFromJson(JSONArray jsonArray) throws JSONException {
        List<Hours> hoursList = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++){
            JSONObject objHours = jsonArray.getJSONObject(i);
            Hours hours = new Hours(objHours.getInt("id"),
                    objHours.getString("day"),
                    objHours.getString("work_start"),
                    objHours.getString("work_end"),
                    objHours.getString("break_start"),
                    objHours.getString("break_end"));
            hoursList.add(hours);
        }
        return hoursList;
    }
}
