package com.manishdear.unofficialcoder.popularmovies.data;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Movie;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.MovieDetails;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.RepoMoviesResult;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Resource;
import com.manishdear.unofficialcoder.popularmovies.ui.movieslist.MoviesFilterType;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface DataSource {

    LiveData<Resource<MovieDetails>> loadMovie(long movieId);

    RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy);

    LiveData<List<Movie>> getAllFavoriteMovies();

    void favoriteMovie(Movie movie);

    void unfavoriteMovie(Movie movie);
}
