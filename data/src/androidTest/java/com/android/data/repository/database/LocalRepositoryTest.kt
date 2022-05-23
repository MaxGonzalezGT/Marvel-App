package com.android.data.repository.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.android.data.data.database.CharacterDao
import com.android.data.data.database.CharactersDatabase
import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.model.CharacterMapper
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class LocalRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var charactersDatabase: CharactersDatabase

    private lateinit var dao: CharacterDao


    @Before
    fun setUp() {
        hiltRule.inject()
        dao = charactersDatabase.characterDao()
    }

    @After
    fun tearDown() {
        charactersDatabase.close()
    }

    @Test
    fun insertCharacter() = runBlocking {

        val input = CharacterEntity(CharacterMapper(1, "Beast", "", "", ""))
        dao.insertOrUpdate(input)

        val allCharacters = dao.readCharacters()
        assertThat(allCharacters).contains(input)

    }

    @Test
    fun readCharacter() = runBlocking {

        val input = CharacterEntity(CharacterMapper(2, "Hulk", "", "", ""))
        dao.insertOrUpdate(input)

        val allCharacters = dao.readCharacters()
        assertThat(allCharacters).contains(input)

    }

}