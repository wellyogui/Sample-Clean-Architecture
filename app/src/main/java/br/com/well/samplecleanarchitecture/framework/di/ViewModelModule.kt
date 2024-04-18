package br.com.well.samplecleanarchitecture.framework.di

import br.com.well.core.repository.NoteRepository
import br.com.well.core.usecase.AddNote
import br.com.well.core.usecase.GetAllNotes
import br.com.well.core.usecase.GetNote
import br.com.well.core.usecase.GetWordCount
import br.com.well.core.usecase.RemoveNote
import br.com.well.samplecleanarchitecture.framework.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    fun provideUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            AddNote(repository),
            GetAllNotes(repository),
            GetNote(repository),
            RemoveNote(repository),
            GetWordCount()
        )
    }
}