package com.example.barbershop.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.models.FavoriteBarbers;

import java.util.ArrayList;

public class FavBarberAdapter extends RecyclerView.Adapter<FavBarberAdapter.ViewHolder>
{
    private ArrayList<FavoriteBarbers> favoriteBarbers;
    private Context context;


    public FavBarberAdapter(Context context, ArrayList<FavoriteBarbers> favoriteBarbers)
    {
        this.context = context;
        this.favoriteBarbers = favoriteBarbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.client_cusomer_barbers_column, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFavBarberName.setText(favoriteBarbers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return favoriteBarbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvFavBarberName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFavBarberName = itemView.findViewById(R.id.fav_barber_name);
        }
    }
}
