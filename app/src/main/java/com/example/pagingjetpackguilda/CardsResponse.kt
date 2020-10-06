package com.example.pagingjetpackguilda

import com.google.gson.annotations.SerializedName

data class CardsResponse(
    @SerializedName("cards")
    val cards: List<MagicCardResponse>
)

data class MagicCardResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("rarity")
    val rarity: String
)