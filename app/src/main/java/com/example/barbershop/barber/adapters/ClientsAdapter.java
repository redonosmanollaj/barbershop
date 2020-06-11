package com.example.barbershop.barber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.models.Client;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder>{

    private List<Client> clients;

    public ClientsAdapter(List<Client> clients){
        this.clients = clients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clients,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(clients.get(position).getName());
        //TODO: setAvatar
    }

    @Override
    public int getItemCount() {
        return clients == null ? 0 : clients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivAvatar;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_client_avatar);
            tvName = itemView.findViewById(R.id.tv_client_name);
        }
    }
}
