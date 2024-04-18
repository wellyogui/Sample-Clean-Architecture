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
class NoteListViewModel @Inject constructor(
    private val useCases: UseCases,
    application: Application
) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val notesLiveData = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        coroutineScope.launch {
            val result = useCases.getAllNotes()
            result.forEach {
                it.wordCount = useCases.getWordCount.invoke(it)
            }
            notesLiveData.postValue(result)
        }
    }
}