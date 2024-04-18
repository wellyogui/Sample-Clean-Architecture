package br.com.well.samplecleanarchitecture.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import br.com.well.core.data.Note
import br.com.well.samplecleanarchitecture.R
import br.com.well.samplecleanarchitecture.databinding.FragmentNoteBinding
import br.com.well.samplecleanarchitecture.framework.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var noteId = 0L
    private val viewModel: NoteViewModel by viewModels<NoteViewModel>()
    private var currentNote = Note("", "", 0L, 0L)

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (R.id.deleteNoteMenu == menuItem.itemId) {
                    AlertDialog.Builder(requireContext()).setTitle("Delete note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes") { dialogInterface, _ ->
                            viewModel.deleteNote(currentNote)
                            dialogInterface.dismiss()
                        }.setNegativeButton("Cancel") { dialogInterface, _ ->
                            dialogInterface.dismiss()
                        }.create().show()
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        with(binding) {
            saveButtonView.setOnClickListener {
                if (titleView.text.isNotEmpty() || contentView.text.isNotEmpty()) {
                    val time = System.currentTimeMillis()
                    currentNote.title = titleView.text.toString()
                    currentNote.content = contentView.text.toString()
                    currentNote.updateTime = time
                    if (currentNote.id == 0L) {
                        currentNote.creationTime = time
                    }

                    viewModel.saveNote(currentNote)
                } else {
                    Navigation.findNavController(it).popBackStack()
                }
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            savedLiveData.observe(viewLifecycleOwner) {
                setupFinishTransaction(it)
            }

            deletedLiveData.observe(viewLifecycleOwner) {
                setupFinishTransaction(it)
            }

            noteLiveData.observe(viewLifecycleOwner) { note ->
                note?.let {
                    currentNote = it
                    binding.titleView.setText(currentNote.title)
                    binding.contentView.setText(currentNote.content)
                }
            }
        }
    }

    private fun setupFinishTransaction(isSuccess: Boolean) {
        if (isSuccess) {
            Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
            hideKeyboard()
            Navigation.findNavController(binding.root).popBackStack()
        } else {
            Toast.makeText(
                context, "Something went wrong, please try again", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

    }
}