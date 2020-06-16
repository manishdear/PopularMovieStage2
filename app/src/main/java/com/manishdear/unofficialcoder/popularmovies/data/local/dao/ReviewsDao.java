package com.manishdear.unofficialcoder.popularmovies.data.local.dao;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Review;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllReviews(List<Review> reviews);

}
