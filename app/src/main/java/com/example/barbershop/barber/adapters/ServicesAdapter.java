package com.example.barbershop.barber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.models.Service;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private List<Service> services;

    public ServicesAdapter(List<Service> services){
        this.services = services;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvServiceName.setText(services.get(position).getName());
        holder.tvServiceDuration.setText(services.get(position).getDurationMinutes());
        holder.tvServiceDescription.setText(services.get(position).getDescription());
        holder.tvServicePrice.setText(services.get(position).getPriceCurrency());
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvServiceName, tvServiceDuration, tvServiceDescription, tvServicePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tv_service_name);
            tvServiceDuration = itemView.findViewById(R.id.tv_service_duration);
            tvServiceDescription = itemView.findViewById(R.id.tv_service_description);
            tvServicePrice = itemView.findViewById(R.id.tv_service_price);
        }
    }
}
