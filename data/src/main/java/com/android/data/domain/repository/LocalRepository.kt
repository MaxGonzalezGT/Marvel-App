package com.android.data.domain.repository

import com.android.data.data.database.entities.CharacterEntity

interface LocalRepository {

    fun readCharacters(): List<CharacterEntity>

    suspend fun insertOrUpdate(characterEntity: CharacterEntity)
}