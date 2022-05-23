package com.android.domain.usecase

import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.repository.Repository
import com.android.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(internetConnection: Boolean,offset:Int): Flow<NetworkResult<List<CharacterEntity>>> = flow {

        emit(NetworkResult.Loading<List<CharacterEntity>>())

        if (internetConnection) {

            try {
                val response = repository.remote.getAllCharacters(offset).data.results.map {
                    it.toCharacter()
                }
                if (response != null && response.isNotEmpty()) {
                    for (result in response) {
                        repository.local.insertOrUpdate(CharacterEntity(result)
                        )
                    }
                    emit(readFromDatabaseOrShowError(""))
                }else{
                    emit(readFromDatabaseOrShowError("Character not found"))
                }


            } catch (e: HttpException) {
                emit(readFromDatabaseOrShowError(e.printStackTrace().toString()))
            } catch (e: IOException) {
                emit(readFromDatabaseOrShowError(e.printStackTrace().toString()))
            }
        } else {
            emit(readFromDatabaseOrShowError("No Internet Connection."))
        }
    }

    /*
    Read from database when API shows error or there is not internet connection. If
    database is empty throw error.
     */
    private fun readFromDatabaseOrShowError(error:String): NetworkResult<List<CharacterEntity>> {
        val readCharacters = repository.local.readCharacters()

        if (readCharacters.isNotEmpty()) {
            return NetworkResult.Success<List<CharacterEntity>>(readCharacters)
        }else{
            return NetworkResult.Error<List<CharacterEntity>>(error)
        }
    }

}