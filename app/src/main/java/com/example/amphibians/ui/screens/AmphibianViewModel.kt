package com.example.amphibians.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.amphibians.AmphibianDetailsApplication
import com.example.amphibians.data.AmphibianDetailsRepository
import com.example.amphibians.model.AmphibianDetails
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val details: List<AmphibianDetails>) : AmphibianUiState
    object Loading : AmphibianUiState
    object Error : AmphibianUiState
}

class AmphibianViewModel(private val amphibianDetailsRepository: AmphibianDetailsRepository) : ViewModel(){
    var amphibianUiState : AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansDetails()
    }

    fun getAmphibiansDetails(){
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianDetailsRepository.getAmphibianDetails())
            } catch (e: IOException){
                AmphibianUiState.Error
            }catch (e: HttpException){
                AmphibianUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianDetailsApplication)
                val amphibianDetailsRepository = application.container.amphibianDetailsRepository
                AmphibianViewModel(amphibianDetailsRepository = amphibianDetailsRepository)
            }
        }
    }
}
