package com.example.pagingjetpackguilda

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MagicCardService {

    @GET("/v1/cards?")
    fun listCards( @Query("page") page: Int,
                   @Query("pageSize") pageSize: Int): Observable<CardsResponse>
}