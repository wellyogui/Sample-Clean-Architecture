package br.com.well.core.repository

import br.com.well.core.data.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.add(note)
    suspend fun getNote(id: Long) = dataSource.get(id)
    suspend fun getAllNotes() = dataSource.getAll()
    suspend fun removeNote(note: Note) = dataSource.remove(note)
}