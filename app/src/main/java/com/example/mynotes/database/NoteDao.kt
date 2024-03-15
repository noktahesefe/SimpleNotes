package com.example.mynotes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.model.Note

/**
 * Data access object for the Note entity.
 */
@Dao
interface NoteDao {

    /**
     * Inserts a note into the database. If the note already exists, replaces it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    /**
     * Updates a note in the database.
     */
    @Update
    suspend fun update(note: Note)

    /**
     * Deletes a note from the database.
     */
    @Delete
    suspend fun delete(note: Note)

    /**
     * Retrieves all notes from the database, ordered by ID in descending order.
     */
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    /**
     * Searches notes by title or description.
     */
    @Query("SELECT * FROM NOTES WHERE title LIKE :query OR description LIKE :query")
    fun searchNotes(query: String?): LiveData<List<Note>>
}
