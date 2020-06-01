package com.example.barbershop.barber.ui.profile;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.barbershop.R;
import com.example.barbershop.barber.adapters.ReviewsAdapter;
import com.example.barbershop.barber.viewmodels.ReviewsViewModel;
import com.example.barbershop.models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

    ReviewsViewModel viewModel;
    ReviewsAdapter reviewsAdapter;
    RecyclerView recyclerView;
    List<Review> reviewList = new ArrayList<>();
    TextView tvNoReviews;


    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_barber_profile_reviews, container, false);

        tvNoReviews = root.findViewById(R.id.tv_no_reviews);

        recyclerView = root.findViewById(R.id.reviews_recyclerview);
        reviewsAdapter = new ReviewsAdapter(reviewList);

        viewModel = ViewModelProviders.of(this).get(ReviewsViewModel.class);
        viewModel.getData().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                if(reviews != null){
                    setReviews(reviews);
                }
            }
        });

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(reviewsAdapter);
    }

    private void setReviews(List<Review> reviews){
        reviewsAdapter.notifyDataSetChanged();
        if(reviews.size() > 0){
            tvNoReviews.setVisibility(View.INVISIBLE);
            for(Review review : reviews){
                reviewList.add(review);
                reviewsAdapter.notifyItemInserted(reviewList.indexOf(review));
            }
        } else {
            tvNoReviews.setVisibility(View.VISIBLE);
        }

    }
}
