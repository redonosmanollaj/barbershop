package com.example.barbershop.barber.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AppointmentsViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private int year;
    private int month;
    private int day;

    public AppointmentsViewModelFactory(Application application, int year, int month, int day){
        this.application = application;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AppointmentsViewModel(application,year,month,day);
    }
}
