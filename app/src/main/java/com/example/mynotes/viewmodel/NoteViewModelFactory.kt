package com.example.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.repository.NoteRepository

/**
 * Factory for creating a NoteViewModel with a constructor that takes an Application and a NoteRepository.
 */
class NoteViewModelFactory(
    private val app: Application,
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Returns an instance of NoteViewModel cast as T.
        // This factory allows for the creation of ViewModels that require non-default constructors.
        return NoteViewModel(app, noteRepository) as T
    }
}
