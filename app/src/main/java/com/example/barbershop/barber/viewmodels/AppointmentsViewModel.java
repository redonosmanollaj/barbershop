package com.example.barbershop.barber.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.barbershop.barber.http.AppointmentsHttpClient;
import com.example.barbershop.barber.ui.AppointmentsFragment;
import com.example.barbershop.models.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsViewModel extends AndroidViewModel {

    private int year;
    private int month;
    private int day;

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }


    private MutableLiveData<List<Appointment>> data;


    public AppointmentsViewModel(@NonNull Application application) {
        super(application);
    }

    public AppointmentsViewModel(@NonNull Application application, int year, int month, int day) {
        super(application);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public LiveData<List<Appointment>> getData(){
        if(data == null){
            data = new MutableLiveData<List<Appointment>>();
            loadData();
        }
        return data;
    }

    public void clearData(){
        if(data != null)
            data.setValue(null);
    }

    public void loadData(){
        new AsyncTask<Void,Void,List<Appointment>>(){

            @Override
            protected List<Appointment> doInBackground(Void... voids) {
                List<Appointment> appointments = new ArrayList<>();
                AppointmentsHttpClient httpClient = new AppointmentsHttpClient(getApplication());
                appointments = httpClient.getAppointments(year,month,day);
                return appointments;
            }

            @Override
            protected void onPostExecute(List<Appointment> appointments) {
                data.setValue(appointments);
            }
        }.execute();
    }

}
