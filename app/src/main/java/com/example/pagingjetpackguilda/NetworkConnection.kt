package com.example.pagingjetpackguilda

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConnection {

    companion object{
        private const val BASE_URL = "https://api.magicthegathering.io"
        val gsonBuilder = GsonBuilder().setLenient().create()

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().readTimeout(30L, TimeUnit.SECONDS).build())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        }
    }
}