package com.example.barbershop.client.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.barbershop.client.http.MyBarbersHttp;
import com.example.barbershop.models.FavoriteBarbers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyBarbersViewModel extends AndroidViewModel
{
    private MutableLiveData<List<FavoriteBarbers>> data;

    public MyBarbersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<FavoriteBarbers>> getData()
    {
        if (data == null)
        {
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData()
    {
        new AsyncTask<Void,Void,List<FavoriteBarbers>>(){

            @Override
            protected List<FavoriteBarbers> doInBackground(Void... voids) {
                List<FavoriteBarbers> favoriteBarbers = new ArrayList<>();
                MyBarbersHttp httpClient = new MyBarbersHttp(getApplication());
                try {
                    favoriteBarbers = httpClient.getFavbarbers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return favoriteBarbers;
            }

            @Override
            protected void onPostExecute(List<FavoriteBarbers> favoriteBarbers) {
                data.setValue(favoriteBarbers);
            }
        }.execute();

    }

}
