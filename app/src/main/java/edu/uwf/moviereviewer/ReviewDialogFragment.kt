package edu.uwf.moviereviewer

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ReviewDialogFragment : DialogFragment() {

    interface OnReviewEnteredListener {
        fun onReviewEntered(name: String, review: String, rating: Float)
    }

    private lateinit var listener: OnReviewEnteredListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.dialog_add_review, null)
        val nameEditText = view.findViewById<EditText>(R.id.movie_name_edit_text)
        val reviewEditText = view.findViewById<EditText>(R.id.movie_review_edit_text)
        val ratingBar = view.findViewById<RatingBar>(R.id.movie_rating_bar)

        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.add_review)
            .setView(view)
            .setPositiveButton(R.string.add) { _, _ ->
                val name = nameEditText.text.toString()
                val review = reviewEditText.text.toString()
                val rating = ratingBar.rating
                listener.onReviewEntered(name, review, rating)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnReviewEnteredListener
    }
}