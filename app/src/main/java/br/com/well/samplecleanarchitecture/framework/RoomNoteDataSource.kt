package br.com.well.samplecleanarchitecture.framework

import android.content.Context
import br.com.well.core.data.Note
import br.com.well.core.repository.NoteDataSource
import br.com.well.samplecleanarchitecture.framework.db.DatabaseService
import br.com.well.samplecleanarchitecture.framework.db.NoteEntity
import javax.inject.Inject

class RoomNoteDataSource @Inject constructor(context: Context) : NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNoteEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllNoteEntities().map { it.toNote() }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
    }
}