package br.com.well.samplecleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.well.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCases: UseCases,
    application: Application
) : AndroidViewModel(application) {


    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val savedLiveData = MutableLiveData<Boolean>()
    val deletedLiveData = MutableLiveData<Boolean>()
    val noteLiveData = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            savedLiveData.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNote(id)
            noteLiveData.postValue(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
            deletedLiveData.postValue(true)
        }
    }
}