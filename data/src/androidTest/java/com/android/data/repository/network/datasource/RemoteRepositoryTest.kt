package com.android.data.repository.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.data.dto.Character
import com.android.data.domain.repository.RemoteDataSource
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class RemoteRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCharacters()= runBlocking {
        val output = remoteDataSource.getAllCharacters(100)
        assertThat(output).isInstanceOf(Character::class.java)
    }

    @Test
    fun getSearchCharacters()= runBlocking {
        val output = remoteDataSource.getSearchedCharacters("beast")
        assertThat(output).isInstanceOf(Character::class.java)
    }

}