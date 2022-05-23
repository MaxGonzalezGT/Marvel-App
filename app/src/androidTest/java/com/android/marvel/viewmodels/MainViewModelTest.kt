package com.android.marvel.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.core.app.ApplicationProvider
import com.android.data.domain.repository.Repository
import com.android.domain.usecase.CharactersUseCase
import com.android.domain.usecase.SearchCharactersUseCase
import com.android.marvel.fakes.FakeDataUtil
import com.android.marvel.fakes.LocalRepositoryFake
import com.android.marvel.fakes.RemoteRepositoryFake
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.android.marvel.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay


@HiltAndroidTest
class MainViewModelTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: Repository
    private lateinit var charactersUseCase: CharactersUseCase
    private lateinit var searchCharactersUseCase: SearchCharactersUseCase
    lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp() {
        hiltRule.inject()
        repository = Repository(RemoteRepositoryFake(), LocalRepositoryFake())
        charactersUseCase = CharactersUseCase(repository)
        searchCharactersUseCase = SearchCharactersUseCase(repository)
        mainViewModel= MainViewModel(charactersUseCase,searchCharactersUseCase, ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCharacters(){
        runBlocking {
            mainViewModel.getCharacters(100)
            delay(10000)
            val value = mainViewModel._charactersResponse.asLiveData().getOrAwaitValue()

            if (value.characters != null && value.characters.isNotEmpty()) {
                assertThat(value.characters[0].name).isEqualTo(FakeDataUtil.CharacterEntity.character1.data.results[0].toCharacter().name)
            }else{
                assertThat(false)
            }
        }
    }

    @Test
    fun searchCharacters(){
        runBlocking {
            mainViewModel.searchCharacters("Beast")
            delay(10000)
            val value = mainViewModel._charactersResponse.asLiveData().getOrAwaitValue()

            if (value.characters != null && value.characters.isNotEmpty()) {
            assertThat(value.characters[0].name).isEqualTo(FakeDataUtil.CharacterEntity.character2.data.results[0].toCharacter().name)
            }else{
                assertThat(false)
            }
        }
    }
}