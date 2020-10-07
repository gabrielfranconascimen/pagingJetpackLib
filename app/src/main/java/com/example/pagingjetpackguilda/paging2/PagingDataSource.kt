package com.example.pagingjetpackguilda.paging2

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
        api.listCards("1", params.requestedLoadSize.toString())
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
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        api.listCards(params., params.requestedLoadSize.toString())
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
            .subscribe(::success, ::error)
    }

    private fun success(magicCards: List<MagicCardEntity>) {

    }

    private fun error(message: String){

    }

}