package com.example.pagingjetpackguilda

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicCardService {

    @GET("/v1/cards?")
    fun listCards( @Query("page") page: String,
                   @Query("pageSize") pageSize: String): Single<CardsResponse>
}