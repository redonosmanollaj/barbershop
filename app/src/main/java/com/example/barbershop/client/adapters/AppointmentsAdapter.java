package com.example.barbershop.client.adapters;

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
import com.example.barbershop.models.Barber;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>
{
    private List<Barber> appointmentList;

    public AppointmentsAdapter (List<Barber> appointmentList) { this.appointmentList = appointmentList; }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointments, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsAdapter.ViewHolder holder, int position) {
        holder.tvBarberName.setText(appointmentList.get(position).getName());
        holder.tvBarbershop.setText(appointmentList.get(position).getBarbershop());
        holder.tvBuilding.setText(appointmentList.get(position).getBuilding());
        holder.tvCity.setText(appointmentList.get(position).getBuilding());
        holder.tvStatus.setText(appointmentList.get(position).getStatus());
        holder.tvStreet.setText(appointmentList.get(position).getStatus());
        holder.tvTime.setText(appointmentList.get(position).getTime());

        switch (appointmentList.get(position).getStatus()){
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
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivAvatar;
        TextView tvBarberName,tvBarbershop, tvTime, tvStatus, tvStreet, tvBuilding, tvCity;

        public ViewHolder(View v)
        {
            super(v);
            ivAvatar = itemView.findViewById(R.id.iv_client_avatar_);
            tvBarberName = itemView.findViewById(R.id.tv_barber_name_);
            tvBarbershop = itemView.findViewById(R.id.tv_address_barbershop);
            tvTime = itemView.findViewById(R.id.tv_time_);
            tvStatus = itemView.findViewById(R.id.tv_status_);
            tvStreet = itemView.findViewById(R.id.tv_address_street_barber);
            tvBuilding = itemView.findViewById(R.id.tv_address_building_barber);
            tvCity = itemView.findViewById(R.id.tv_address_city_barber);
        }
    }
}
