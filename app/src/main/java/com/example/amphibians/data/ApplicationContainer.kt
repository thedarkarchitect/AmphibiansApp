package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json


interface AppContainer {
    val amphibianDetailsRepository : AmphibianDetailsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =  "https://android-kotlin-fun-mars-server.appspot.com/"

    @kotlinx.serialization.ExperimentalSerializationApi
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


    @OptIn(ExperimentalSerializationApi::class)
    private val retrofitService : AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    override val amphibianDetailsRepository: AmphibianDetailsRepository by lazy {
        NetworkAmphibianDetailsRepository(retrofitService)
    }
}