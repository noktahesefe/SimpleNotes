package com.example.mynotes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.mynotes.MainActivity
import com.example.mynotes.model.Note
import com.example.mynotes.viewmodel.NoteViewModel
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentAddNoteBinding

/**
 * Fragment for adding a new note.
 * Implements MenuProvider for adding action menu items specific to this fragment.
 */
class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var addNoteBinding: FragmentAddNoteBinding? = null
    // Safe access to the binding instance, ensuring it's not null when accessed.
    private val binding get() = addNoteBinding!!

    // ViewModel to interact with the notes database.
    private lateinit var noteViewModel: NoteViewModel

    // A reference to the root view of this fragment.
    private lateinit var addNoteView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using data binding.
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Registers this fragment as a MenuProvider to contribute items to the Options Menu.
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        addNoteView = view
        // Retrieves NoteViewModel from MainActivity to share between fragments.
        noteViewModel = (activity as MainActivity).noteViewModel
    }

    /**
     * Saves the note to the database.
     */
    private fun saveNote(view : View) {
        val title = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if(title.isNotEmpty()){
            val note = Note(0, title, noteDesc)
            noteViewModel.addNote(note)
            Toast.makeText(addNoteView.context,"Note Saved", Toast.LENGTH_SHORT).show()
            // Navigates back to the home fragment after saving.
            view.findNavController().popBackStack(R.id.homeFragment, false)
        }
        else{
            Toast.makeText(addNoteView.context,"Title is required", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Creates the options menu specific to this fragment.
     */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    /**
     * Handles action items clicks in the options menu.
     */
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Prevents memory leak by nullifying the binding when the view is destroyed.
        addNoteBinding = null
    }
}
