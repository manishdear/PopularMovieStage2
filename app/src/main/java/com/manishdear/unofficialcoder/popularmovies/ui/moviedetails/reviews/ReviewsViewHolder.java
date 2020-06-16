package com.manishdear.unofficialcoder.popularmovies.ui.moviedetails.reviews;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Review;
import com.manishdear.unofficialcoder.popularmovies.databinding.ItemReviewBinding;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsViewHolder extends RecyclerView.ViewHolder {

    private ItemReviewBinding binding;

    public ReviewsViewHolder(@NonNull ItemReviewBinding binding) {
        super(binding.getRoot());

        this.binding = binding;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.frame.setClipToOutline(false);
        }
    }

    public void bindTo(final Review review) {
        String userName = review.getAuthor();

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(userName.substring(0, 1).toUpperCase(), color);
        binding.imageAuthor.setImageDrawable(drawable);

        binding.textAuthor.setText(userName);

        binding.textContent.setText(review.getContent());

        binding.executePendingBindings();
    }

    public static ReviewsViewHolder create(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemReviewBinding binding =
                ItemReviewBinding.inflate(layoutInflater, parent, false);
        return new ReviewsViewHolder(binding);
    }
}
