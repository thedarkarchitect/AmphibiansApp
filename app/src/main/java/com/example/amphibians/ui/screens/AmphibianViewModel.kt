package com.example.amphibians.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val details: String) : AmphibianUiState
    object Loading : AmphibianUiState
    object Error : AmphibianUiState
}

class AmphibianViewModel : ViewModel(){
    var amphibianUiState : AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansDetails()
    }

    private fun getAmphibiansDetails(){
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                val results = AmphibianApi.retrofitService.getAmphibians()
                AmphibianUiState.Success(results)
            } catch (e: IOException){
                AmphibianUiState.Error
            }catch (e: HttpException){
                AmphibianUiState.Error
            }

        }
    }
}