package edu.uwf.moviereviewer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.uwf.moviereviewer.model.Review
import edu.uwf.moviereviewer.viewmodel.ReviewListViewModel

class MainActivity  : AppCompatActivity(),
    ReviewDialogFragment.OnReviewEnteredListener {

    private var reviewAdapter = ReviewAdapter(mutableListOf())
    private lateinit var subjectRecyclerView: RecyclerView
    private lateinit var subjectColors: IntArray
    private val reviewListViewModel: ReviewListViewModel by lazy {
        ViewModelProvider(this).get(ReviewListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)



        subjectColors = resources.getIntArray(R.array.subjectColors)

        findViewById<FloatingActionButton>(R.id.add_review_button).setOnClickListener {
            addReviewClick() }

        subjectRecyclerView = findViewById(R.id.review_recycler_view)
        subjectRecyclerView.layoutManager = LinearLayoutManager(applicationContext)

        // Show the subjects
        reviewListViewModel.reviewListLiveData.observe(this, { reviewList ->
            updateUI(reviewList)
        },)
    }

    private fun updateUI(subjectList: List<Review>) {
        reviewAdapter = ReviewAdapter(subjectList as MutableList<Review>)
        subjectRecyclerView.adapter = reviewAdapter
    }

    override fun onReviewEntered(reviewText: String) {
        if (reviewText.isNotEmpty()) {
            val review = Review(0, reviewText)
            reviewListViewModel.addReview(review)


            Toast.makeText(this, "Added $reviewText", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addReviewClick() {
        val dialog = ReviewDialogFragment()
        dialog.show(supportFragmentManager, "subjectDialog")
    }

    private inner class ReviewHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_items, parent, false)),
        View.OnClickListener {

        private var review: Review? = null
        private val reviewTextView: TextView

        init {
            itemView.setOnClickListener(this)
            reviewTextView = itemView.findViewById(R.id.review_text_view)
        }

        fun bind(review: Review, position: Int) {
            this.review = review
            reviewTextView.text = this.review!!.name

            // Make the background color dependent on the length of the subject string
            val colorIndex = this.review!!.review.length % subjectColors.size
            reviewTextView.setBackgroundColor(subjectColors[colorIndex])
        }

        override fun onClick(view: View) {
            // Start QuestionActivity with the selected subject




        }
    }

    private inner class ReviewAdapter(private val reviewList: MutableList<Review>) :
        RecyclerView.Adapter<ReviewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
            val layoutInflater = LayoutInflater.from(applicationContext)
            return ReviewHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
            holder.bind(reviewList[position], position)
        }

        override fun getItemCount(): Int {
            return reviewList.size
        }
    }
}