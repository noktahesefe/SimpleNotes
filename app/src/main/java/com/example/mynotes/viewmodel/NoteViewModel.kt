package com.example.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.model.Note
import com.example.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app)
{


    fun getAllNotes() = noteRepository.getAllNotes()
    fun searchNotes(query: String?) = noteRepository.searchNotes(query)

    fun addNote(note : Note) = viewModelScope.launch { noteRepository.insert(note) }

    fun deleteNote(note : Note) = viewModelScope.launch { noteRepository.delete(note) }

    fun updateNote(note: Note) = viewModelScope.launch { noteRepository.update(note) }


}