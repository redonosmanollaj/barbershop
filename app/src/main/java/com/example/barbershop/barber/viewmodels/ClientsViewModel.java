package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.barber.http.ClientsHttpClient;
import com.example.barbershop.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Client>> data;

    public ClientsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Client>> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    private void loadData(){
        new AsyncTask<Void,Void,List<Client>>(){

            @Override
            protected List<Client> doInBackground(Void... voids) {
                List<Client> clients = new ArrayList<>();
                ClientsHttpClient httpClient = new ClientsHttpClient(getApplication());
                clients = httpClient.getClients();
                return clients;
            }

            @Override
            protected void onPostExecute(List<Client> clients) {
                data.setValue(clients);
            }
        }.execute();
    }
}
