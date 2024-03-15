package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.repository.NoteRepository
import com.example.mynotes.viewmodel.NoteViewModel
import com.example.mynotes.viewmodel.NoteViewModelFactory
import com.example.mynotes.R

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up the ViewModel for the activity.
        setupViewModel()
    }

    private fun setupViewModel() {
        // Creating an instance of the NoteRepository using the NoteDatabase.
        val noteRepository = NoteRepository(NoteDatabase(this))

        // Instantiating the NoteViewModelFactory with the Application instance and NoteRepository.
        val noteViewModelFactory = NoteViewModelFactory(application, noteRepository)

        // Getting the NoteViewModel instance using the ViewModelProvider.
        noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
    }
}
