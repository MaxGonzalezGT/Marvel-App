package com.android.data.di

import com.android.data.data.network.MarvelApi
import com.android.data.domain.repository.RemoteRepository
import com.android.data.domain.repository.RemoteDataSource
import com.android.space.data.network.util.CustomDispatcher
import com.android.space.data.network.util.MockNetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestNetworkModule {

    @Singleton
    @Provides
    fun provideMockServer(): MockWebServer {
        return MockWebServer().apply {
            dispatcher = CustomDispatcher()
        }
    }

    @Provides
    @Singleton
    fun provideMarvelApi(mockWebServer: MockWebServer): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url(MockNetworkConfig.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }


    @Singleton
    @Provides
    fun provideApiService(api: MarvelApi): RemoteRepository {
        return RemoteDataSource(api)
    }
}