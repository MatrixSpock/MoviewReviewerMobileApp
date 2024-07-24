package edu.uwf.moviereviewer.repo

import androidx.lifecycle.LiveData
import androidx.room.*

import edu.uwf.moviereviewer.model.Review

@Dao
interface ReviewDao {
    @Query("SELECT * FROM Review WHERE id = :id")
    fun getReview(id: Long): Review?

    @Query("SELECT * FROM Review ORDER BY ID COLLATE NOCASE")
    fun getReviews(): LiveData<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review): Long

    @Update
    fun updateReview(review: Review)

    @Delete
    fun deleteReview(review: Review)
}