package com.android.data.domain.repository


import javax.inject.Inject
import com.android.data.data.dto.Character
import com.android.data.data.network.MarvelApi


class RemoteDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : RemoteRepository {

    override suspend fun getAllCharacters(offset:Int): Character {
        return marvelApi.getAllCharacters(offset=offset.toString())
    }

    override suspend fun getSearchedCharacters(query:String): Character {
        return marvelApi.getSearchedCharacters(query=query)
    }
}