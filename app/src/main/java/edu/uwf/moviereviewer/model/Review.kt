package edu.uwf.moviereviewer.model
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Review(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @NonNull
    var name: String = "",

    var review: String = "") {
}