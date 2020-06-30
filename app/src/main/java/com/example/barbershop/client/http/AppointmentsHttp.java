package com.example.barbershop.client.http;

import android.content.Context;

import com.example.barbershop.models.Appointment;
import com.example.barbershop.models.Barber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AppointmentsHttp extends BaseHttpClient
{
    private static final String URL = BASE_URL + "/api/client/appointments/upcoming";
    private List<Barber> appointments = new ArrayList<>();
    public AppointmentsHttp(Context c) {
        super(c);
    }

    public List<Barber> getAppointments()
    {
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", "Bearer "+token)
                .build();
        try
        {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null)
            {
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                List<Barber> appointmentToBarber = new ArrayList<>();
                for (int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Barber barber = new Barber(
                            jsonObject.getJSONObject("barber").getString("name"),
                            jsonObject.getJSONObject("barber").getJSONObject("location").getString("barbershop"),
                            jsonObject.getJSONObject("barber").getJSONObject("location").getString("street"),
                            jsonObject.getJSONObject("barber").getJSONObject("location").getString("building"),
                            jsonObject.getJSONObject("barber").getJSONObject("location").getString("city"),
                            jsonObject.getString("status"));

                    appointmentToBarber.add(barber);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
