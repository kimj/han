package com.mentalmachines.han.di

import android.content.Context
import androidx.room.Room
import com.mentalmachines.han.Constants.HAN_DATABASE
import com.mentalmachines.han.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, HanDatabase::class.java, HAN_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideHanjaDao(hanDatabase: HanDatabase): HanjaDao {
        return hanDatabase.hanjaDao()
    }

    @Provides
    fun provideDictionaryDao(HanDatabase: HanDatabase): DictionaryDao {
        return HanDatabase.dictionaryDao()
    }

    @Provides
    fun provideFlashCardDao(HanDatabase: HanDatabase): FlashCardDao {
        return HanDatabase.flashCardDao()
    }

    @Provides
    fun provideGrammarDao(HanDatabase: HanDatabase): GrammarDao {
        return HanDatabase.grammarDao()
    }

}