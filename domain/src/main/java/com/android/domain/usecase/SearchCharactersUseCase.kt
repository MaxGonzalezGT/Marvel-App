package com.android.domain.usecase

import com.android.data.data.dto.Result
import com.android.data.data.database.entities.CharacterEntity
import com.android.data.domain.model.CharacterMapper
import com.android.data.domain.repository.Repository
import com.android.data.util.Constants.Companion.CHARACTER_NOT_FOUND
import com.android.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
        internetConnection: Boolean,
        query:String
    ): Flow<NetworkResult<List<CharacterEntity>>> = flow {

        emit(NetworkResult.Loading<List<CharacterEntity>>())

        if (internetConnection) {

            try {
                val response = repository.remote.getSearchedCharacters(query).data.results.map {
                    it
                }

                if (response != null && response.isNotEmpty()) {
                    var characters = mutableListOf<CharacterEntity>()
                    for (result in response) {
                        characters.add(
                            CharacterEntity(
                                result.toCharacter()
                            )
                        )
                    }
                    emit(NetworkResult.Success<List<CharacterEntity>>(characters))
                } else {
                    emit(NetworkResult.Error<List<CharacterEntity>>(CHARACTER_NOT_FOUND))
                }
            } catch (e: HttpException) {
                emit(NetworkResult.Error<List<CharacterEntity>>(e.printStackTrace().toString()))
            } catch (e: IOException) {
                emit(NetworkResult.Error<List<CharacterEntity>>(e.printStackTrace().toString()))
            }
        } else {
            emit(NetworkResult.Error<List<CharacterEntity>>("No Internet Connection."))
        }
    }
}