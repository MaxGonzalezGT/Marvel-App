package com.android.domain.fakes

import com.android.data.data.dto.Character
import com.android.data.domain.repository.RemoteRepository

class RemoteRepositoryFake : RemoteRepository {
    override suspend fun getAllCharacters(offset: Int): Character {
        return FakeDataUtil.CharacterEntity.character1
    }

    override suspend fun getSearchedCharacters(query: String): Character {
        return FakeDataUtil.CharacterEntity.character2
    }

}