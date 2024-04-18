package br.com.well.core.usecase

import br.com.well.core.data.Note
import br.com.well.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}