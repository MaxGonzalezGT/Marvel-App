package com.android.data.domain.repository

import com.android.data.data.dto.Character

interface RemoteRepository {

    suspend fun getAllCharacters(offset:Int): Character
    suspend fun getSearchedCharacters(query:String): Character

}