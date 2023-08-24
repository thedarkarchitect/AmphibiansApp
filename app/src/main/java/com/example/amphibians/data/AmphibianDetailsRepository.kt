package com.example.amphibians.data

import com.example.amphibians.model.AmphibianDetails
//import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianApiService
//import com.example.amphibians.ui.screens.AmphibianUiState

interface AmphibianDetailsRepository {
    suspend fun getAmphibianDetails(): List<AmphibianDetails>
}

class NetworkAmphibianDetailsRepository(
    private val amphibianApiService: AmphibianApiService
): AmphibianDetailsRepository{
    override suspend fun getAmphibianDetails(): List<AmphibianDetails> = amphibianApiService.getAmphibians()
}