package br.com.well.samplecleanarchitecture.framework.di

import br.com.well.samplecleanarchitecture.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun providesApp() = Application()
}