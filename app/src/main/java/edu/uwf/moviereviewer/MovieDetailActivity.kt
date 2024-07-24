package edu.uwf.moviereviewer

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.uwf.moviereviewer.viewmodel.ReviewListViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var reviewTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var deleteButton: Button
    private lateinit var reviewListViewModel: ReviewListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        nameTextView = findViewById(R.id.movie_name)
        reviewTextView = findViewById(R.id.movie_review)
        ratingBar = findViewById(R.id.movie_rating)
        deleteButton = findViewById(R.id.delete_button)

        reviewListViewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)

        val reviewId = intent.getLongExtra("REVIEW_ID", -1)
        if (reviewId != -1L) {
            loadReviewDetails(reviewId)
        }

        deleteButton.setOnClickListener {
            showDeleteConfirmationDialog(reviewId)
        }
    }

    private fun loadReviewDetails(reviewId: Long) {
        val review = reviewListViewModel.getReview(reviewId)
        review?.let {
            nameTextView.text = it.name
            reviewTextView.text = it.review
            ratingBar.rating = it.rating
        }
    }

    private fun showDeleteConfirmationDialog(reviewId: Long) {
        AlertDialog.Builder(this)
            .setTitle("Delete Review")
            .setMessage("Are you sure you want to delete this review?")
            .setPositiveButton("Delete") { _, _ ->
                deleteReview(reviewId)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteReview(reviewId: Long) {
        val review = reviewListViewModel.getReview(reviewId)
        review?.let {
            reviewListViewModel.deleteReview(it)
        }
    }
}