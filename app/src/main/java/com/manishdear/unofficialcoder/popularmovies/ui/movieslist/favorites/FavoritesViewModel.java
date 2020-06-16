package com.manishdear.unofficialcoder.popularmovies.ui.movieslist.favorites;

import com.manishdear.unofficialcoder.popularmovies.data.MovieRepository;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel {

    private LiveData<List<Movie>> favoriteListLiveData;

    public FavoritesViewModel(MovieRepository repository) {
        favoriteListLiveData = repository.getAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getFavoriteListLiveData() {
        return favoriteListLiveData;
    }
}
