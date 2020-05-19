package com.example.barbershop.barber.http;

import android.content.Context;

import com.example.barbershop.models.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReviewsHttpClient extends BaseHttpClient {

    private static final String REVIEWS_PATH = "/api/reviews";

    public ReviewsHttpClient(Context c) {
        super(c);
    }

    public List<Review> getReviews(){
        List<Review> reviews = new ArrayList<>();
        Request request = new Request.Builder()
                .url(BASE_URL+REVIEWS_PATH)
                .addHeader("Authorization","Bearer "+token)
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if(responseBody != null){
                String json = responseBody.string();
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Review review = new Review(jsonObject.getInt("id"),
                                                jsonObject.getJSONObject("client").getString("name"),
                                                jsonObject.getInt("stars"),
                                                jsonObject.getString("content"),
                                                jsonObject.getString("created_at"));
                    reviews.add(review);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
