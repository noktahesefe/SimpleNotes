package com.example.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.model.Note

/**
 * Abstract database class that serves as the main access point for the underlying connection to the app's persisted data.
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    /**
     * Defines the DAOs that work with the database. Allows for abstract access to the database.
     */
    abstract fun getNoteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var instance: NoteDatabase? = null
        private val LOCK = Any()

        /**
         * Returns the singleton instance of the database.
         */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        /**
         * Creates the database. This is a fallback in case the database doesn't exist.
         */
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }
}
