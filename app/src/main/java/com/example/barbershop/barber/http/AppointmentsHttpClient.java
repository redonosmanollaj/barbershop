package com.example.barbershop.barber.http;

import android.app.DownloadManager;
import android.content.Context;

import com.example.barbershop.models.Appointment;
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

public class AppointmentsHttpClient extends BaseHttpClient{

    private int year;
    private int month;
    private int day;

    public static final String APPOINTMENTS_PATH = "/api/barber/appointments";

    public AppointmentsHttpClient(Context c) {
        super(c);
    }

    public AppointmentsHttpClient(int year, int month, int day, Context c){
        super(c);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public List<Appointment> getAppointments(){
        List<Appointment> appointments = new ArrayList<>();

        Request request = new Request.Builder()
                .url(BASE_URL+APPOINTMENTS_PATH)
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
                    JSONArray jsonServices = jsonObject.getJSONArray("services");
                    List<Service> services = new ArrayList<>();
                    for(int j=0;j<jsonServices.length();j++){
                        Service service = new Service(
                            jsonServices.getJSONObject(j).getInt("id"),
                            jsonServices.getJSONObject(j).getString("name"),
                            jsonServices.getJSONObject(j).getString("price"),
                            jsonServices.getJSONObject(j).getInt("duration"),
                            jsonServices.getJSONObject(j).getString("description")
                        );
                        services.add(service);
                    }

                    JSONObject client = jsonObject.getJSONObject("client");
                    JSONObject barber = jsonObject.getJSONObject("barber");

                    Appointment appointment = new Appointment(
                            jsonObject.getInt("id"),
                            client.getString("name"),
                            barber.getString("name"),
                            services,
                            jsonObject.getString("date_time"),
                            jsonObject.getString("status")
                    );
                    appointments.add(appointment);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
