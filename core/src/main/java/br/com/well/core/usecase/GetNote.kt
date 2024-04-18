package br.com.well.core.usecase

import br.com.well.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}