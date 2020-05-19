package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.barber.http.ServicesHttpClient;
import com.example.barbershop.models.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Service>> data;

    public ServicesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Service>> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    private void loadData(){
        new AsyncTask<Void,Void,List<Service>>(){

            @Override
            protected List<Service> doInBackground(Void... voids) {
                List<Service> services = new ArrayList<>();
                ServicesHttpClient httpClient = new ServicesHttpClient(getApplication());
                services = httpClient.getServices();

                return services;
            }

            @Override
            protected void onPostExecute(List<Service> services) {
                data.setValue(services);
            }
        }.execute();
    }
}
