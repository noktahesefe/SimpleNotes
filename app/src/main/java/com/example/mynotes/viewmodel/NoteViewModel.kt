package com.example.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.model.Note
import com.example.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * ViewModel providing data to the UI and surviving configuration changes.
 * Acts as a communication center between the Repository and the UI.
 */
class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app) {

    /**
     * Launches a coroutine to insert a note asynchronously.
     */
    fun addNote(note : Note) = viewModelScope.launch { noteRepository.insert(note) }

    /**
     * Launches a coroutine to delete a note asynchronously.
     */
    fun deleteNote(note : Note) = viewModelScope.launch { noteRepository.delete(note) }

    /**
     * Launches a coroutine to update a note asynchronously.
     */
    fun updateNote(note: Note) = viewModelScope.launch { noteRepository.update(note) }

    /**
     * Exposes LiveData of all notes for observation by the UI.
     */
    fun getAllNotes() = noteRepository.getAllNotes()

    /**
     * Exposes LiveData of the search results for observation by the UI.
     */
    fun searchNotes(query: String?) = noteRepository.searchNotes(query)
}
