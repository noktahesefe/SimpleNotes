package com.example.mynotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a note entity in the application.
 * Annotated with @Entity to signify a SQLite table for Room database.
 * Implements Parcelable interface to allow note objects to be passed between components.
 */
@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) // Marks 'id' as the primary key with autoincrement.
    val id: Int,

    val title: String, // Title of the note.

    val description: String // Description or content of the note.

): Parcelable // Enables Note objects to be parcelable, simplifying data passing.
