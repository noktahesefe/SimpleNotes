package com.example.mynotes.repository

import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.model.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insert(note: Note) = db.getNoteDao().insert(note)
    suspend fun update(note: Note) = db.getNoteDao().update(note)
    suspend fun delete(note: Note) = db.getNoteDao().delete(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNotes(query: String?) = db.getNoteDao().searchNotes(query)

}