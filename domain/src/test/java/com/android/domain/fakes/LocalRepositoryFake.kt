package com.android.domain.fakes

import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.repository.LocalRepository

class LocalRepositoryFake : LocalRepository {
    override fun readCharacters(): List<CharacterEntity> {
        var characters = mutableListOf<CharacterEntity>()
        characters.add(
            CharacterEntity(
                FakeDataUtil.CharacterEntity.character1.data.results[0].toCharacter()
            )
        )

        return characters
    }

    override suspend fun insertOrUpdate(characterEntity: CharacterEntity) {
        var insertedCharacter = FakeDataUtil.CharacterEntity.character1
    }
}