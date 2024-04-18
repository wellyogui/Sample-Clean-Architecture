package br.com.well.samplecleanarchitecture.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.well.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_date") val creationTime: Long,
    @ColumnInfo(name = "update_time") val updateTime: Long
) {
    companion object {
        fun fromNote(note: Note): NoteEntity {
            return NoteEntity(note.id, note.title, note.content, note.creationTime, note.updateTime)
        }
    }

    fun toNote(): Note {
        return Note(title, content, creationTime, updateTime, id)
    }
}