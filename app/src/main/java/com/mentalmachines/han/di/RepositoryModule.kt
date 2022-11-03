package com.mentalmachines.han.di

import com.mentalmachines.han.data.repository.GrammarRepository
import com.mentalmachines.han.data.repository.GrammarRepositoryImpl
import com.mentalmachines.han.data.repository.HanjaRepository
import com.mentalmachines.han.data.repository.HanjaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesDictionaryRepository(impl: DictionaryRepositoryImpl): DictionaryRepository

    @Binds
    abstract fun providesGrammarRepository(impl: GrammarRepositoryImpl): GrammarRepository

    @Binds
    abstract fun providesFlashCardRepository(impl: FlashCardRepositoryImpl): FlashCardRepository

    @Binds
    abstract fun providesHanjaRepository(impl: HanjaRepositoryImpl): HanjaRepository

}
