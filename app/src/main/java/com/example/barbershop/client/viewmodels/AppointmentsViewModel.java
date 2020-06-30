package com.example.barbershop.client.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.barbershop.client.http.AppointmentsHttp;
import com.example.barbershop.models.Appointment;
import com.example.barbershop.models.Barber;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsViewModel extends AndroidViewModel
{
    private MutableLiveData<List<Barber>> data;

    public AppointmentsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Barber>> getData ()
    {
        if(data == null)
        {
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }


    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<Void, Void, List<Barber>>() {

            @Override
            protected List<Barber> doInBackground(Void... voids) {
                List<Barber> appointments = new ArrayList<>();
                AppointmentsHttp httpClient = new AppointmentsHttp(getApplication());
                appointments = httpClient.getAppointments();

                return appointments;
            }
            @Override
            protected void onPostExecute(List<Barber> appointmentList) {
                data.setValue(appointmentList);
            }
        }.execute();
    }
}
