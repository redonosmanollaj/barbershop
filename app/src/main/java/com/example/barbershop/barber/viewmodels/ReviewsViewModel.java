package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.barber.http.ReviewsHttpClient;
import com.example.barbershop.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Review>> data;

    public ReviewsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Review>> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    private void loadData(){
        new AsyncTask<Void,Void,List<Review>>(){

            @Override
            protected List<Review> doInBackground(Void... voids) {
                List<Review> reviews = new ArrayList<>();
                ReviewsHttpClient httpClient = new ReviewsHttpClient(getApplication());
                reviews = httpClient.getReviews();
                return reviews;
            }

            @Override
            protected void onPostExecute(List<Review> reviews) {
                data.setValue(reviews);
            }
        }.execute();
    }
}
