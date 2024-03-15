package com.example.mynotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.model.Note
import com.example.mynotes.databinding.NoteLayoutBinding
import androidx.navigation.findNavController
import com.example.mynotes.fragment.HomeFragmentDirections

/**
 * Adapter for the RecyclerView in HomeFragment to display notes.
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    /**
     * Provides a reference to the views for each data item.
     */
    class NoteViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    /**
     * A callback for calculating the diff between two non-null items in a list.
     */
    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            // Notes are the same if their ids are identical
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            // If the data content of notes are the same
            return oldItem == newItem
        }
    }

    /**
     * Helper for computing the difference between two lists via DiffUtil on a background thread.
     */
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Inflates the item view
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // Binds data to the item view
        val note = differ.currentList[position]
        holder.itemBinding.apply {
            noteTitle.text = note.title
            noteDesc.text = note.description

            root.setOnClickListener {
                // Navigation action to EditNoteFragment with current note
                val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note)
                it.findNavController().navigate(direction)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
