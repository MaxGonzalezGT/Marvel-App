package com.android.data.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Transaction
import androidx.room.Query
import com.android.data.data.database.entities.CharacterEntity


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterEntity: CharacterEntity): Long

    @Update
    suspend fun update(characterEntity: CharacterEntity): Int

    @Transaction
    suspend fun insertOrUpdate(characterEntity: CharacterEntity): Long {
        if(characterEntity.id>0) {
            val id = insert(characterEntity)
            return if (id == -1L) {
                update(characterEntity)
                characterEntity.id
            } else {
                id
            }
        }
        return 0
    }

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    fun readCharacters(): List<CharacterEntity>

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()

}