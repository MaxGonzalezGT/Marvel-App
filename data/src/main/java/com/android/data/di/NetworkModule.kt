package com.android.data.di

import com.android.data.data.network.MarvelApi
import com.android.data.domain.repository.RemoteRepository
import com.android.data.domain.repository.RemoteDataSource
import com.android.data.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiService(api:MarvelApi): RemoteRepository {
        return RemoteDataSource(api)
    }
}