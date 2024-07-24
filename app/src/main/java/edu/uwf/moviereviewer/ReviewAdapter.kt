package edu.uwf.moviereviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uwf.moviereviewer.model.Review

class ReviewAdapter(
    private var reviews: List<Review>,
    private val onItemClick: (Review) -> Unit
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_items, parent, false)
        return ReviewViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount() = reviews.size

    fun updateReviews(newReviews: List<Review>) {
        reviews = newReviews
        notifyDataSetChanged()
    }

    class ReviewViewHolder(
        itemView: View,
        private val onItemClick: (Review) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val reviewTextView: TextView = itemView.findViewById(R.id.review_text_view)

        fun bind(review: Review) {
            reviewTextView.text = review.name
            itemView.setOnClickListener { onItemClick(review) }
        }
    }
}