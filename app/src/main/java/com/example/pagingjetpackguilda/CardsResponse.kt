package com.example.pagingjetpackguilda

import com.google.gson.annotations.SerializedName

data class CardsResponse(
    @SerializedName("cards")
    val cards: List<MagicCardResponse>
)

