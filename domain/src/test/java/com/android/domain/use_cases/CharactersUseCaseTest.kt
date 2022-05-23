package com.android.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.repository.Repository
import com.android.domain.fakes.FakeDataUtil
import com.android.domain.fakes.LocalRepositoryFake
import com.android.domain.fakes.RemoteRepositoryFake
import com.android.domain.util.TestCoroutineRule
import com.android.domain.usecase.CharactersUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersUseCaseTest{

    private lateinit var repository: Repository
    private lateinit var charactersUseCase: CharactersUseCase

    @get:Rule
    val testCoroutineRule = TestCoroutineRule ()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = Repository(RemoteRepositoryFake(),LocalRepositoryFake())
        charactersUseCase = CharactersUseCase(repository)
    }

    @Test
    fun `get Characters from API`() {
        runBlocking {
            charactersUseCase(true,100).collect {
                var data = it.data.let { it?: emptyList<CharacterEntity>()}
                if (data != null && data.isNotEmpty()) {
                    assertThat(data[0].name).isEqualTo(FakeDataUtil.CharacterEntity.character1.data.results[0].toCharacter().name)
                }else{
                    assertThat(false)
                }
            }
        }
    }

    @Test
    fun `get Characters from DataBase`() {
        runBlocking {
            charactersUseCase(false,100).collect {
                var data = it.data.let { it?: emptyList<CharacterEntity>()}
                if (data != null && data.isNotEmpty()) {
                    assertThat(data[0].name).isEqualTo(FakeDataUtil.CharacterEntity.character1.data.results[0].toCharacter().name)
                }else{
                    assertThat(false)
                }
            }
        }
    }

}