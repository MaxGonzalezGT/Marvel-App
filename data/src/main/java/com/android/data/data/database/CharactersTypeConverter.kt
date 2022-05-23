package com.android.data.data.database

import androidx.room.TypeConverter
import com.android.data.domain.model.CharacterMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharactersTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun characterToString(character: CharacterMapper): String {
        return gson.toJson(character)
    }

    @TypeConverter
    fun stringToCharacter(data: String): CharacterMapper {
        val listType = object : TypeToken<CharacterMapper>() {}.type
        return gson.fromJson(data, listType)
    }
}