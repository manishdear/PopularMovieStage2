package com.manishdear.unofficialcoder.popularmovies.ui.movieslist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Movie;
import com.manishdear.unofficialcoder.popularmovies.databinding.ItemMovieBinding;
import com.manishdear.unofficialcoder.popularmovies.ui.moviedetails.DetailsActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final ItemMovieBinding binding;

    public MovieViewHolder(@NonNull ItemMovieBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bindTo(final Movie movie) {
        binding.setMovie(movie);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA_MOVIE_ID, movie.getId());
                view.getContext().startActivity(intent);
            }
        });

        binding.executePendingBindings();
    }

    public static MovieViewHolder create(ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemMovieBinding binding =
                ItemMovieBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(binding);
    }
}
