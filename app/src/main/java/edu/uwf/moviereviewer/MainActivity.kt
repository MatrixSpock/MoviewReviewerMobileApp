package edu.uwf.moviereviewer

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.uwf.moviereviewer.model.Review
import edu.uwf.moviereviewer.viewmodel.ReviewListViewModel

class MainActivity : AppCompatActivity(), ReviewDialogFragment.OnReviewEnteredListener {

    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var reviewListViewModel: ReviewListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        reviewListViewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)

        setupRecyclerView()
        setupFab()
        setupSearchView()

        reviewListViewModel.reviewListLiveData.observe(this, { reviewList ->
            updateUI(reviewList)
        })
    }

    private fun setupRecyclerView() {
        reviewRecyclerView = findViewById(R.id.review_recycler_view)
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewAdapter = ReviewAdapter(emptyList()) { review ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("REVIEW_ID", review.id)
            startActivity(intent)
        }
        reviewRecyclerView.adapter = reviewAdapter
    }

    private fun setupFab() {
        findViewById<FloatingActionButton>(R.id.add_review_button).setOnClickListener {
            val dialog = ReviewDialogFragment()
            dialog.show(supportFragmentManager, "reviewDialog")
        }
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                reviewListViewModel.setFilter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                reviewListViewModel.setFilter(newText)
                return true
            }
        })
    }

    private fun updateUI(reviewList: List<Review>) {
        reviewAdapter.updateReviews(reviewList)
        if (reviewList.isEmpty()) {
            // Show "No reviews found" message
        } else {
            // Hide "No reviews found" message
        }
    }

    override fun onReviewEntered(name: String, review: String, rating: Float) {
        val newReview = Review(name = name, review = review, rating = rating)
        reviewListViewModel.addReview(newReview)
    }
}