package com.manishdear.unofficialcoder.popularmovies.ui.moviedetails.reviews;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Review;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsBinding {

    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<Review> items) {
        ReviewsAdapter adapter = (ReviewsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }
}
