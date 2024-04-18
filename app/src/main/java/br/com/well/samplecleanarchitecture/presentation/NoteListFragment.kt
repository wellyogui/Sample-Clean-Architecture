package br.com.well.samplecleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.com.well.samplecleanarchitecture.databinding.FragmentNoteListBinding
import br.com.well.samplecleanarchitecture.framework.NoteListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels<NoteListViewModel>()
    private val adapter = NotesListAdapter(arrayListOf(), object : AdapterListener {
        override fun onItemClicked(id: Long) {
            gotToNoteDetails(id)
        }

    })

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        binding.noteListView.adapter = adapter
        binding.addNoteView.setOnClickListener {
            gotToNoteDetails()
        }
    }

    private fun observeViewModel() {
        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            binding.loadingView.visibility = GONE
            adapter.updateNotes(it.sortedByDescending { it.updateTime })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }

    private fun gotToNoteDetails(id: Long = 0) {
        val action = NoteListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.root).navigate(action)
    }
}