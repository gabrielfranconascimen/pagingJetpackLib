package com.example.pagingjetpackguilda

import io.reactivex.Single

class Repository {

    fun fetchData(): Single<CardsResponse> {
        val retrofitClient = NetworkConnection.getInstance()
        val endpoint = retrofitClient.create(MagicCardService::class.java)

        return endpoint.listCards("1", "4")
    }
}