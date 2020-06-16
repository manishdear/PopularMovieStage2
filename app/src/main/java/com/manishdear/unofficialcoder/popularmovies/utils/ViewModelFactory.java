package com.manishdear.unofficialcoder.popularmovies.utils;

import com.manishdear.unofficialcoder.popularmovies.data.MovieRepository;
import com.manishdear.unofficialcoder.popularmovies.ui.moviedetails.MovieDetailsViewModel;
import com.manishdear.unofficialcoder.popularmovies.ui.movieslist.discover.DiscoverMoviesViewModel;
import com.manishdear.unofficialcoder.popularmovies.ui.movieslist.favorites.FavoritesViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    public static ViewModelFactory getInstance(MovieRepository repository) {
        return new ViewModelFactory(repository);
    }

    private ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DiscoverMoviesViewModel.class)) {

            return (T) new DiscoverMoviesViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {

            return (T) new FavoritesViewModel(repository);
        } else if (modelClass.isAssignableFrom(MovieDetailsViewModel.class)) {

            return (T) new MovieDetailsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
