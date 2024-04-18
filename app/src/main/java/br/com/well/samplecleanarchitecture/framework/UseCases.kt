package br.com.well.samplecleanarchitecture.framework

import br.com.well.core.usecase.AddNote
import br.com.well.core.usecase.GetAllNotes
import br.com.well.core.usecase.GetNote
import br.com.well.core.usecase.GetWordCount
import br.com.well.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)