package com.example.pagingjetpackguilda.paging2

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.pagingjetpackguilda.MagicCardEntity
import com.example.pagingjetpackguilda.MagicCardService
import com.example.pagingjetpackguilda.NetworkConnection
import rx.android.schedulers.AndroidSchedulers

import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers

class PagingDataSource: PageKeyedDataSource<Int, MagicCardEntity>() {

    private val api = NetworkConnection.getInstance().create(MagicCardService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MagicCardEntity>
    ) {
        api.listCards(1, params.requestedLoadSize)
            .map{cardsResponse ->
                cardsResponse.cards.map { magicCardResponse ->
                    MagicCardEntity(
                        magicCardResponse.name,
                        magicCardResponse.type,
                        magicCardResponse.rarity
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy( onNext = {
                callback.onResult(it, 1, 2)
            }, onError = {
                //TODO nothing for all
                Log.i("errorF", it.message ?: "")
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        //nothing to do
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        api.listCards(params.key, params.requestedLoadSize)
            .map {cardsResponses ->
                val newList = arrayListOf<MagicCardEntity>()
                cardsResponses.cards.map {cardResponse ->
                    MagicCardEntity(
                        cardResponse.name,
                        cardResponse.type,
                        cardResponse.rarity
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy( onNext = {
                callback.onResult(it, params.key)
            }, onError = {
                Log.i("errorF", it.message ?: "")
                //TODO nothing for all
            })
    }
}