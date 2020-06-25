package com.example.barbershop.client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barbershop.R;
import com.example.barbershop.models.Barber;
import java.util.List;

public class SearchedBarbersAdapter extends RecyclerView.Adapter<SearchedBarbersAdapter.ViewHolder> {

    private List<Barber> barbers;

    public SearchedBarbersAdapter (List<Barber> barbers) { this.barbers = barbers; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_search_results_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedBarbersAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(barbers.get(position).getName());
        holder.tvCity.setText(barbers.get(position).getCity());
    }


    @Override
    public int getItemCount() {
        return barbers == null ? 0 : barbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_barber);
            tvCity = itemView.findViewById(R.id.tv_city_barber);
        }
    }
}
