package br.com.well.samplecleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.well.core.data.Note
import br.com.well.samplecleanarchitecture.databinding.ItemLayoutBinding
import java.text.SimpleDateFormat
import java.util.Date

class NotesListAdapter(var notes: ArrayList<Note>, val listener: AdapterListener) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            with(binding) {
                titleView.text = item.title
                contentView.text = item.content
                dateView.text = "Last update: ${sdf.format(Date(item.updateTime))}"
                wordCountView.text = "Words: ${item.wordCount}"
                noteCardView.setOnClickListener {
                    listener.onItemClicked(item.id)
                }
            }
        }
    }
}

interface AdapterListener {
    fun onItemClicked(id: Long)
}