package com.example.mynotes.repository

import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.model.Note

/**
 * Repository class abstracting access to note data sources.
 * Facilitates communication between the ViewModel and the database layer.
 */
class NoteRepository(private val db: NoteDatabase) {

    /**
     * Inserts a note into the database.
     */
    suspend fun insert(note: Note) = db.getNoteDao().insert(note)

    /**
     * Updates an existing note in the database.
     */
    suspend fun update(note: Note) = db.getNoteDao().update(note)

    /**
     * Deletes a note from the database.
     */
    suspend fun delete(note: Note) = db.getNoteDao().delete(note)

    /**
     * Retrieves all notes from the database.
     */
    fun getAllNotes() = db.getNoteDao().getAllNotes()

    /**
     * Searches notes based on a query string.
     */
    fun searchNotes(query: String?) = db.getNoteDao().searchNotes(query)
}
