package com.example.barbershop.client.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.client.adapters.AppointmentsAdapter;
import com.example.barbershop.client.viewmodels.AppointmentsViewModel;
import com.example.barbershop.models.Appointment;
import com.example.barbershop.models.Barber;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsFragment extends Fragment {
    private View v;
    private List<Barber> appointmentList = new ArrayList<>();
    private AppointmentsViewModel viewModel;
    private RecyclerView recyclerView;
    private AppointmentsAdapter adapter;
    private TextView tvNoAppointments;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_client_appointments, container, false);
        recyclerView = v.findViewById(R.id.appointments_recyclerview_);
        adapter = new AppointmentsAdapter(appointmentList);
        tvNoAppointments = v.findViewById(R.id.tv_no_appointments_);

        viewModel = ViewModelProviders.of(this).get(AppointmentsViewModel.class);
        getData();

        return v;
    }

    private void getData() {
        viewModel.getData().observe(this, new Observer<List<Barber>>() {
            @Override
            public void onChanged(List<Barber> appointments) {
                if (appointments != null) setAppointment(appointments);
            }
        });
    }

    private void setAppointment(List<Barber> appointments)
    {
        appointmentList.clear();
        adapter.notifyDataSetChanged();
        if (appointments.size() <= 0)
            tvNoAppointments.setVisibility(View.VISIBLE);
        else
        {
            for (Barber appointment : appointments)
            {
                appointmentList.add(appointment);
                adapter.notifyItemInserted(appointmentList.indexOf(appointment));
            }
        }
    }
}
