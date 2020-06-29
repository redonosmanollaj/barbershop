package com.example.barbershop.client.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barbershop.R;
import com.example.barbershop.client.ui.fragments.ProfileFragment;
import com.example.barbershop.models.Barber;
import java.util.List;

public class SearchedBarbersAdapter extends RecyclerView.Adapter<SearchedBarbersAdapter.ViewHolder> {

    private List<Barber> barbers;
    private onBarberListener onBarberListener;

    public SearchedBarbersAdapter (List<Barber> barbers, onBarberListener onBarberListener)
    {
        this.barbers = barbers;
        this.onBarberListener = onBarberListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_search_results_row, parent, false);
        return new ViewHolder(view, onBarberListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvCity, tvPhone, tvBarbershop, tvStreet, tvBuilding, tvCountry;
        RatingBar rbReviews;


        onBarberListener onBarberListener;

        public ViewHolder(@NonNull View itemView, onBarberListener onBarberListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_barber);
            tvCity = itemView.findViewById(R.id.tv_city_barber);


            this.onBarberListener = onBarberListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBarberListener.onBarberClick(getAdapterPosition());
            Bundle bundle = new Bundle();

            ProfileFragment fragment = new ProfileFragment();
            bundle.putString("barbername", tvName.getText().toString());
            fragment.setArguments(bundle);
        }
    }

    public interface onBarberListener {
        void onBarberClick(int position);
    }
}
