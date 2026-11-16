package com.example.week7soal1.repository

import com.example.week7soal1.model.PokemonCard
import com.example.week7soal1.service.ApiConfig
import com.example.week7soal1.service.RetrofitClient

class CardRepository {
    private val service = RetrofitClient.service

    suspend fun searchCards(query: String): List<PokemonCard> {
        val response = service.searchCards("Bearer ${ApiConfig.API_KEY}", query)
        return response.data ?: emptyList()
    }
}