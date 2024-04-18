package br.com.well.samplecleanarchitecture.framework.di

import android.content.Context
import br.com.well.core.repository.NoteDataSource
import br.com.well.core.repository.NoteRepository
import br.com.well.samplecleanarchitecture.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideNoteDataSource(@ApplicationContext context: Context): NoteDataSource {
        return RoomNoteDataSource(context)
    }

    @Provides
    fun provideNoteRepository(dataSource: NoteDataSource): NoteRepository {
        return NoteRepository(dataSource)
    }
}