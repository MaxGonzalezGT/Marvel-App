package com.android.data.domain.repository

import com.android.data.data.database.CharacterDao
import com.android.data.data.database.entities.CharacterEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
):LocalRepository {

    override fun readCharacters(): List<CharacterEntity> {
        return characterDao.readCharacters()
    }

    override suspend fun insertOrUpdate(characterEntity: CharacterEntity) {
        characterDao.insertOrUpdate(characterEntity)
    }
}