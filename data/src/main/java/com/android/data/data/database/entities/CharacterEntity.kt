package com.android.data.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.data.domain.model.CharacterMapper
import com.android.data.util.Constants.Companion.CHARACTER_TABLE

@Entity(tableName = CHARACTER_TABLE)
class CharacterEntity(
    var character: CharacterMapper
) {
    @PrimaryKey(autoGenerate = false)
    var id: Long = character.id.toLong()

    var name: String = character.name
}