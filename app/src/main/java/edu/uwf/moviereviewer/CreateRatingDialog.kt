package edu.uwf.moviereviewer
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class CreateRatingDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {

        val inflater = requireActivity().layoutInflater;
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(inflater.inflate(R.layout.createratingdialog, null))
        builder.setTitle("woooo")
        builder.setMessage("Now were gettign somewhere")
        builder.setPositiveButton("hell yep", null)
        builder.setNegativeButton("cancel", null)
        return builder.create()
    }
}