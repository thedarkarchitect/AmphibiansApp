package com.example.amphibians.network

import com.example.amphibians.model.AmphibianDetails
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import kotlinx.serialization.json.Json
//import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
//private val retrofit = Retrofit.Builder()
////    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//    .baseUrl(BASE_URL)
//    .build()

interface AmphibianApiService{
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianDetails>
}

//object AmphibianApi {
//    val retrofitService : AmphibianApiService by lazy {
//        retrofit.create(AmphibianApiService::class.java)
//    }
//}