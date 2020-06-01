package com.example.barbershop.barber.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.models.Appointment;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {

    private List<Appointment> appointments;

    public AppointmentsAdapter(List<Appointment> appointments){
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointments,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // AVATAR
        holder.tvClientName.setText(appointments.get(position).getClientName());
        holder.tvServices.setText(appointments.get(position).getServices());
        holder.tvTime.setText(appointments.get(position).getFormatedTime());
        holder.tvStatus.setText(appointments.get(position).getStatus().toUpperCase());

        switch (appointments.get(position).getStatus().toString()){
            case "Requested" : holder.tvStatus.setTextColor(Color.YELLOW);
            break;
            case "Canceled" : holder.tvStatus.setTextColor(Color.parseColor("#ffa500"));
            break;
            case "Confirmed" : holder.tvStatus.setTextColor(Color.CYAN);
            break;
            case "Completed" : holder.tvStatus.setTextColor(Color.GREEN);
            break;
            case "No show" : holder.tvStatus.setTextColor(Color.GRAY);
            break;
        }
    }

    @Override
    public int getItemCount() {
        return appointments == null ? 0 : appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivAvatar;
        public TextView tvClientName,tvServices, tvTime,tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_client_avatar);
            tvClientName = itemView.findViewById(R.id.tv_client_name);
            tvServices = itemView.findViewById(R.id.tv_services);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }

    }
}
