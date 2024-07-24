package edu.uwf.moviereviewer.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import edu.uwf.moviereviewer.model.Review

class ReviewRepository private constructor(context: Context) {

    companion object {
        private var instance: ReviewRepository? = null

        fun getInstance(context: Context): ReviewRepository {
            if (instance == null) {
                instance = ReviewRepository(context)
            }
            return instance!!
        }
    }

    private val database: ReviewDatabase = Room.databaseBuilder(
        context.applicationContext,
        ReviewDatabase::class.java,
        "review.db"
    ).allowMainThreadQueries().build()

    private val reviewDao = database.reviewDao()

    fun getReview(reviewId: Long): Review? = reviewDao.getReview(reviewId)

    fun getReviews(): LiveData<List<Review>> = reviewDao.getReviews()

    fun getFilteredReviews(query: String): LiveData<List<Review>> = reviewDao.getFilteredReviews(query)

    fun addReview(review: Review) {
        review.id = reviewDao.addReview(review)
    }

    fun deleteReview(review: Review) = reviewDao.deleteReview(review)
}