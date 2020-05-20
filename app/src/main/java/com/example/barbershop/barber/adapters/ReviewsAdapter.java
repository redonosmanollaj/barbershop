package com.example.barbershop.barber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.R;
import com.example.barbershop.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<Review> reviews ;

    public ReviewsAdapter(List<Review> reviews){
        this.reviews = reviews;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvClientName.setText(reviews.get(position).getClientName());
        holder.tvElapsedTime.setText(reviews.get(position).getElapsedTime());
        holder.tvComment.setText(reviews.get(position).getContent());
        holder.ratingBar.setProgress(reviews.get(position).getStars());
        // AVATAR
    }

    @Override
    public int getItemCount() {
        return reviews == null ? 0 : reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivAvatar;
        public TextView tvClientName,tvElapsedTime,tvComment;
        public RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvClientName = itemView.findViewById(R.id.tv_review_name);
            tvElapsedTime = itemView.findViewById(R.id.tv_review_elapsed_time);
            tvComment = itemView.findViewById(R.id.tv_review_comment);
            ratingBar = itemView.findViewById(R.id.review_rating_bar);
        }
    }
}
