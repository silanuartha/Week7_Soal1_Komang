package com.example.week7soal1.model

data class CardsResponse(
    val data: List<PokemonCard>? = null
)

data class PokemonCard(
    val id: String? = null,
    val name: String? = null,
    val setName: String? = null,
    val cardNumber: String? = null,
    val rarity: String? = null,
    val cardType: String? = null,
    val artist: String? = null,
    val imageCdnUrl400: String? = null,
    val imageCdnUrl200: String? = null,
    val prices: Prices? = null
)

data class Prices(
    val market: Double? = null,
    val low: Double? = null,
    val listings: Int? = null,
    val sellers: Int? = null,
    val primaryPrinting: String? = null
)