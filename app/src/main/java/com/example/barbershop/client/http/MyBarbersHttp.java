package com.example.barbershop.client.http;

import android.content.Context;
import android.util.Log;

import com.example.barbershop.GlobalVariables;
import com.example.barbershop.client.http.BaseHttpClient;
import com.example.barbershop.models.FavoriteBarbers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MyBarbersHttp extends BaseHttpClient
{
    private static final String myBarbersURL = BASE_URL + "/api/mybarbers";

    public MyBarbersHttp(Context c) {
        super(c);
    }

    public List<FavoriteBarbers> getFavbarbers() throws IOException {
        List<FavoriteBarbers> favbarbers = new ArrayList<>();

        Request request = new Request.Builder()
                .url(myBarbersURL)
                .addHeader("Authorization", "Bearer "+token)
                .build();
        try
        {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if(responseBody != null)
            {
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    FavoriteBarbers favoriteBarber = new FavoriteBarbers(
                            jsonObject.getString("name"),
                            jsonObject.getString("phone")
                    );
                    favbarbers.add(favoriteBarber);
                }
            }
        }
        catch(IOException | JSONException e)
        {
            e.printStackTrace();
        }
        return favbarbers;
    }

}
