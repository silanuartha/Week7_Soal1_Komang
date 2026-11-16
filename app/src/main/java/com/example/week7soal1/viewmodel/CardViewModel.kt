package com.example.week7soal1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week7soal1.model.PokemonCard
import com.example.week7soal1.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {
    private val repository = CardRepository()

    var query by mutableStateOf("Charizard")
    var cards by mutableStateOf<List<PokemonCard>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var selectedCard by mutableStateOf<PokemonCard?>(null)

    init {
        search()
    }

    fun search() {
        if (query.isBlank()) return

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = repository.searchCards(query.trim())
                cards = result
                if (result.isEmpty()) {
                    errorMessage = "Card tidak ditemukan"
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Terjadi kesalahan"
            }
            isLoading = false
        }
    }
}