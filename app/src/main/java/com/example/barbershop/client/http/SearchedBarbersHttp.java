package com.example.barbershop.client.http;

import android.content.Context;
import android.util.Log;

import com.example.barbershop.client.ui.fragments.SearchFragment;
import com.example.barbershop.models.Barber;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchedBarbersHttp extends BaseHttpClient {
    private String searchedBarbersURL = BASE_URL + "/api/barbers/search";

    public SearchedBarbersHttp(Context c){
        super(c);
    }


    public List<Barber> getSearchedBarbers()
    {
        List<Barber> searchedBarbers = new ArrayList<>();


        RequestBody requestBody = new FormBody.Builder()
                .add("name", SearchFragment.GetName()) //how to pass the EditText value from SearchFragment???
                .build();

        Request request = new Request.Builder()
                .url(searchedBarbersURL)
                .addHeader("Authorization", "Bearer "+token)
                .method("POST", requestBody)
                .build();

        Log.i("RequestDebug", request.toString());
        try {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null)
            {
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Barber barber = new Barber(
                            jsonObject.getString("name"),
                            jsonObject.getString("phone"),
                            jsonObject.getJSONObject("location").getInt("id"),
                            jsonObject.getJSONObject("location").getInt("user_id"),
                            jsonObject.getJSONObject("location").getString("barbershop"),
                            jsonObject.getJSONObject("location").getString("street"),
                            jsonObject.getJSONObject("location").getString("building"),
                            jsonObject.getJSONObject("location").getString("city"),
                            jsonObject.getJSONObject("location").getString("country")
                    );
                    searchedBarbers.add(barber);
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return searchedBarbers;
    }

}
