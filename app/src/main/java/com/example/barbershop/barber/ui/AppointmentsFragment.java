package com.example.barbershop.barber.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.barber.adapters.AppointmentsAdapter;
import com.example.barbershop.barber.viewmodels.AppointmentsViewModel;
import com.example.barbershop.models.Appointment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentsFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private AppointmentsAdapter adapter;
    private List<Appointment> appointmentList = new ArrayList<>();
    private AppointmentsViewModel viewModel;


    public AppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_barber_appointments, container, false);

        calendarView = root.findViewById(R.id.calendarView);
        recyclerView = root.findViewById(R.id.appointments_recyclerview);
        adapter = new AppointmentsAdapter(appointmentList);
        viewModel = ViewModelProviders.of(this).get(AppointmentsViewModel.class);

        calendarView.setSelectedDate(CalendarDay.today());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay calendarDay = calendarView.getSelectedDate();
            }
        });

        viewModel.getData().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                if(appointments != null){
                    setAppointments(appointments);
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context c = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.setAdapter(adapter);
    }

    private void setAppointments(List<Appointment> appointments){
        adapter.notifyDataSetChanged();
        for (Appointment appointment : appointments){
            appointmentList.add(appointment);
            adapter.notifyItemInserted(appointmentList.indexOf(appointment));
        }
    }
}
