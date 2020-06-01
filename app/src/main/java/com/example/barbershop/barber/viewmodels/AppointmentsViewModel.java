package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.barber.http.AppointmentsHttpClient;
import com.example.barbershop.models.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Appointment>> data;

    public AppointmentsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Appointment>> getData(){
        if(data == null){
            data = new MutableLiveData<List<Appointment>>();
            loadData();
        }
        return data;
    }

    private void loadData(){
        new AsyncTask<Void,Void,List<Appointment>>(){

            @Override
            protected List<Appointment> doInBackground(Void... voids) {
                List<Appointment> appointments = new ArrayList<>();
                AppointmentsHttpClient httpClient = new AppointmentsHttpClient(getApplication());
                appointments = httpClient.getAppointments();
                return appointments;
            }

            @Override
            protected void onPostExecute(List<Appointment> appointments) {
                data.setValue(appointments);
            }
        }.execute();
    }
}
