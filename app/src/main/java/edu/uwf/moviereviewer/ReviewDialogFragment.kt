package edu.uwf.moviereviewer
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ReviewDialogFragment: DialogFragment() {

    interface OnReviewEnteredListener {
        fun onReviewEntered(reviewText: String)
    }

    private lateinit var listener: OnReviewEnteredListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val nameEditText = EditText(requireActivity())
        nameEditText.inputType = InputType.TYPE_CLASS_TEXT
        nameEditText.maxLines = 1

        val reviewEditText = EditText(requireActivity())
        reviewEditText.inputType = InputType.TYPE_CLASS_TEXT
        reviewEditText.maxLines = 1

        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.Review)
            .setView(nameEditText)
            .setView(reviewEditText)
            .setPositiveButton(R.string.add_review) { dialog, whichButton ->
                // Notify listener
                val name = nameEditText.text.toString()
                val review = reviewEditText.text.toString()
                listener.onReviewEntered(name.trim())
                listener.onReviewEntered(review.trim())
            }

            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnReviewEnteredListener
    }
}