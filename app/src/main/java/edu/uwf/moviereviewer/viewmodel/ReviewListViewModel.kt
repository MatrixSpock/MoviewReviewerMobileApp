package edu.uwf.moviereviewer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import edu.uwf.moviereviewer.model.Review
import edu.uwf.moviereviewer.repo.ReviewRepository

class ReviewListViewModel(application: Application) : AndroidViewModel(application) {

    private val reviewRepo = ReviewRepository.getInstance(application.applicationContext)
    private val filterQuery = MutableLiveData<String?>()

    val reviewListLiveData: LiveData<List<Review>> = filterQuery.switchMap { query ->
        if (query.isNullOrBlank()) {
            reviewRepo.getReviews()
        } else {
            reviewRepo.getFilteredReviews(query)
        }
    }

    fun addReview(review: Review) = reviewRepo.addReview(review)

    // Add these new methods
    fun getReview(reviewId: Long): Review? = reviewRepo.getReview(reviewId)

    fun deleteReview(review: Review) = reviewRepo.deleteReview(review)

    fun setFilter(query: String?) {
        filterQuery.value = query ?:""
    }
}