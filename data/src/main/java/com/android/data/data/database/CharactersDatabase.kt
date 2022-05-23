package com.android.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.data.data.database.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CharactersTypeConverter::class)
abstract class CharactersDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}