package com.example.barbershop.client.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.barbershop.client.http.BarberServicesHttp;
import com.example.barbershop.models.Service;
import java.util.ArrayList;
import java.util.List;

public class BarberServicesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Service>> data;

    public BarberServicesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Service>> getData()
    {
        if(data == null){
            data = new MutableLiveData<>();
            loadData();
        }
        Log.d("services", "getData: "+data);
        return data;
    }


    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<Void,Void,List<Service>>(){
            @Override
            protected List<Service> doInBackground(Void... voids) {
                List<Service> services = new ArrayList<>();
                BarberServicesHttp httpClient = new BarberServicesHttp(getApplication());
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
