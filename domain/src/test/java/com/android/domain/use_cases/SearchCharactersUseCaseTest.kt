package com.android.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.repository.Repository
import com.android.domain.fakes.FakeDataUtil
import com.android.domain.fakes.LocalRepositoryFake
import com.android.domain.fakes.RemoteRepositoryFake
import com.android.domain.usecase.CharactersUseCase
import com.android.domain.usecase.SearchCharactersUseCase
import com.android.domain.util.TestCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchCharactersUseCaseTest{

    private lateinit var repository: Repository
    private lateinit var searchCharactersUseCase: SearchCharactersUseCase

    @get:Rule
    val testCoroutineRule = TestCoroutineRule ()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = Repository(RemoteRepositoryFake(), LocalRepositoryFake())
        searchCharactersUseCase = SearchCharactersUseCase(repository)
    }

    @Test
    fun `get searched character`() {
        runBlocking {
            searchCharactersUseCase(true,"Beast").collect {
                var data = it.data.let { it?: emptyList<CharacterEntity>()}
                if (data != null && data.isNotEmpty()) {
                    Truth.assertThat(data[0].name).isEqualTo(FakeDataUtil.CharacterEntity.character2.data.results[0].toCharacter().name)
                }
            }
        }
    }
}