package br.com.well.core.usecase

import br.com.well.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}