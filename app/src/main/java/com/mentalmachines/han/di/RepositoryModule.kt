package com.mentalmachines.han.di

import android.content.Context
import androidx.room.Room
import com.mentalmachines.han.Constants.HANJA_DATABASE
import com.mentalmachines.han.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    abstract fun providesDictionaryRepository(impl: DictionaryRepository): DictionaryRepository

    @Binds
    abstract fun providesGrammarRepository(impl: GrammarRepository): GrammarRepository

    @Binds
    abstract fun providesFlashCardRepository(impl: FlashCardRepository): FlashCardRepository

    @Binds
    abstract fun providesHanjaRepository(impl: HanjaRepository): HanjaRepository
}*/

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, HanjaDatabase::class.java, HANJA_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: HanjaDatabase) = db.hanjaDao()

    /*@Provides
    fun provideEntity() = Hanja()*/


}