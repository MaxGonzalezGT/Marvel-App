package com.android.data.di

import android.content.Context
import androidx.room.Room
import com.android.data.data.database.CharacterDao
import com.android.data.data.database.CharactersDatabase
import com.android.data.domain.repository.LocalDataSource
import com.android.data.domain.repository.LocalRepository
import com.android.data.util.Constants.Companion.DATABASE_NAME
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
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CharactersDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: CharactersDatabase) = database.characterDao()

    @Singleton
    @Provides
    fun provideLocalRepository(
        dao: CharacterDao
    ) = LocalDataSource(dao) as LocalRepository

}