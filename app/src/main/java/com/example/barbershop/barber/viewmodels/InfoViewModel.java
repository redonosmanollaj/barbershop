package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.barber.http.InfoHttpClient;
import com.example.barbershop.barber.http.LocationHttpClient;
import com.example.barbershop.models.Info;
import com.example.barbershop.models.Location;

import java.util.ArrayList;
import java.util.List;

public class InfoViewModel extends AndroidViewModel {

    private MutableLiveData<Info> data;

    public InfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Info> getData(){
        if(data == null){
            data = new MutableLiveData<Info>();
            loadData();
        }
        return data;
    }

    private void loadData(){
        new AsyncTask<Void,Void,Info>(){

            @Override
            protected Info doInBackground(Void... voids) {
                Info info = new Info();
                InfoHttpClient httpClient = new InfoHttpClient(getApplication());
                info = httpClient.getInfo();
                return info;
            }

            @Override
            protected void onPostExecute(Info info) {
                data.setValue(info);
            }
        }.execute();
    }
}
