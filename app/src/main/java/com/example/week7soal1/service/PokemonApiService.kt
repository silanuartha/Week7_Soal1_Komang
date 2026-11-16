package com.example.week7soal1.service

import com.example.week7soal1.model.CardsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

object ApiConfig {
    const val BASE_URL = "https://www.pokemonpricetracker.com/api/v2/"
    const val API_KEY = "pokeprice_free_9244ae1fe48c83e6805860d6d530dbbf1331cbf1b98a1c55"
}

interface PokemonApiService {
    @GET("cards")
    suspend fun searchCards(
        @Header("Authorization") authorization: String,
        @Query("search") query: String,
        @Query("limit") limit: Int = 20
    ): CardsResponse
}

object RetrofitClient {
    val service: PokemonApiService = Retrofit.Builder()
        .baseUrl(ApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApiService::class.java)
}