package com.example.barbershop.client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barbershop.R;
import com.example.barbershop.models.FavoriteBarbers;
import java.util.List;

public class FavBarberAdapter extends RecyclerView.Adapter<FavBarberAdapter.ViewHolder>
{
    private List<FavoriteBarbers> favoriteBarbers;


    public FavBarberAdapter (List <FavoriteBarbers> favoriteBarbers)
    {
        this.favoriteBarbers = favoriteBarbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.client_customer_barbers_column, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvFavBarberName.setText(favoriteBarbers.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return favoriteBarbers == null ? 0 : favoriteBarbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvFavBarberName;
        public Button btnAppoint;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvFavBarberName = itemView.findViewById(R.id.fav_barber_name);
            btnAppoint = itemView.findViewById(R.id.book_barber);

        }
    }
}
