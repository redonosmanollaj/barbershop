package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.models.Hours;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HoursHttpClient extends BaseHttpClient{

    private int id;
    private String day;
    private String workStart;
    private String workEnd;
    private String breakStart;
    private String breakEnd;

    private static final String HOURS_PATH = "/api/hours";

    public HoursHttpClient(Context c){
        super(c);
    }

    public HoursHttpClient(Context c, int id, String day, String workStart, String workEnd, String breakStart, String breakEnd) {
        super(c);
        this.id = id;
        this.day = day;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
    }

    public List<Hours> getHours(){
        List<Hours> hoursList = new ArrayList<>();

        Request request = new Request.Builder()
                .url(BASE_URL+HOURS_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Hours hours = new Hours(
                            jsonObject.getInt("id"),
                            jsonObject.getString("day"),
                            jsonObject.getString("work_start"),
                            jsonObject.getString("work_end"),
                            jsonObject.getString("break_start"),
                            jsonObject.getString("break_end")
                    );
                    hoursList.add(hours);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hoursList;
    }
}
