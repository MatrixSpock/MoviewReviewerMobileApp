package edu.uwf.moviereviewer.viewmodel;
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import edu.uwf.moviereviewer.model.Review
import edu.uwf.moviereviewer.repo.ReviewRepository;


public class ReviewListViewModel(application: Application): AndroidViewModel(application) {


    private val reviewRepo = ReviewRepository.getInstance(application.applicationContext)

    val reviewListLiveData: LiveData<List<Review>> = reviewRepo.getReviews()

    fun addReview(review: Review) = reviewRepo.addReview(review)

    fun deleteSubject(review: Review) = reviewRepo.deleteReview(review)
    fun getReviews(): LiveData<List<Review>> = reviewRepo.getReviews()
}
