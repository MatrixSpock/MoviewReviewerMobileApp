package edu.uwf.moviereviewer.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uwf.moviereviewer.model.Review

@Database(entities = [Review::class], version = 1)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
}