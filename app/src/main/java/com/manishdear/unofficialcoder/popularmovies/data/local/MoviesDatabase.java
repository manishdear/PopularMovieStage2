package com.manishdear.unofficialcoder.popularmovies.data.local;

import android.content.Context;

import com.manishdear.unofficialcoder.popularmovies.data.local.dao.CastsDao;
import com.manishdear.unofficialcoder.popularmovies.data.local.dao.MoviesDao;
import com.manishdear.unofficialcoder.popularmovies.data.local.dao.ReviewsDao;
import com.manishdear.unofficialcoder.popularmovies.data.local.dao.TrailersDao;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Cast;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Converters;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Movie;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Review;
import com.manishdear.unofficialcoder.popularmovies.data.local.model.Trailer;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {Movie.class, Trailer.class, Cast.class, Review.class},
        version = 1,
        exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MoviesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Movies.db";

    private static MoviesDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract MoviesDao moviesDao();

    public abstract TrailersDao trailersDao();

    public abstract CastsDao castsDao();

    public abstract ReviewsDao reviewsDao();

    public static MoviesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static MoviesDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MoviesDatabase.class,
                DATABASE_NAME).build();
    }
}
