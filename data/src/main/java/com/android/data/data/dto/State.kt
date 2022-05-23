package com.android.data.data.dto

import com.android.data.data.database.entities.CharacterEntity


data class State(
    val isLoading : Boolean = false,
    val characters : List<CharacterEntity> = emptyList<CharacterEntity>(),
    val error : String = ""
)