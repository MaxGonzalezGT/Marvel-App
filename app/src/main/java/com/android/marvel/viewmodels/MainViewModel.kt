package com.android.marvel.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.data.data.dto.Result
import com.android.data.data.database.entities.CharacterEntity
import com.android.data.data.dto.State
import com.android.data.util.Constants.Companion.CHARACTER_NOT_FOUND
import com.android.data.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.android.domain.usecase.CharactersUseCase
import com.android.domain.usecase.SearchCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase,
    application: Application
) : AndroidViewModel(application) {

    var networkStatus = false
    var backOnline = false

    private val charactersResponse = MutableStateFlow(State())
    var _charactersResponse : StateFlow<State> = charactersResponse

    fun getCharacters(offset:Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersResponse.value = State(isLoading = true)
            charactersUseCase(hasInternetConnection(),offset).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        var data = it.data.let { it?: emptyList<CharacterEntity>()}
                        if (data != null && data.isNotEmpty()) {
                            charactersResponse.value =
                                State(characters = data)
                        }else{
                            State(
                                error = it.message ?: CHARACTER_NOT_FOUND
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        charactersResponse.value =
                            State(isLoading = true)
                    }
                    is NetworkResult.Error -> {
                        charactersResponse.value =
                            State(
                                error = it.message ?: "An Unexpected Error"
                            )
                    }
                }
            }
    }

    fun searchCharacters(query:String) = viewModelScope.launch(Dispatchers.IO) {
        charactersResponse.value = State(isLoading = true)
        searchCharactersUseCase(hasInternetConnection(),query).collect {
            when (it) {
                is NetworkResult.Success -> {
                    var data = it.data.let { it?: emptyList<CharacterEntity>()}
                    if (data != null && data.isNotEmpty()) {
                        var characters = mutableListOf<CharacterEntity>()
                        for (result in data) {
                            characters.add(
                                CharacterEntity(
                                    result.character
                                )
                            )
                        }
                        charactersResponse.value =
                            State(characters = characters)
                    }else{
                        State(
                            error = it.message ?: CHARACTER_NOT_FOUND
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    charactersResponse.value =
                       State(isLoading = true)
                }
                is NetworkResult.Error -> {
                    charactersResponse.value =
                        State(
                            error = it.message ?: "An Unexpected Error"
                        )
                }
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}