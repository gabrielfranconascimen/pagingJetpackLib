package com.example.pagingjetpackguilda.paging2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.pagingjetpackguilda.MagicCardEntity
import com.example.pagingjetpackguilda.MagicCardService
import com.example.pagingjetpackguilda.NetworkConnection
import rx.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Action

import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers

class PagingDataSource: PageKeyedDataSource<Int, MagicCardEntity>() {

    val initialState = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    private val api = NetworkConnection.getInstance().create(MagicCardService::class.java)

    private var retry: Action? = null
    fun tryAgain() {
        retry?.run()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MagicCardEntity>
    ) {
        initialState.postValue(NetworkState.LOADING)

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
                initialState.postValue(NetworkState.LOADED)
            }, onError = {
                //TODO nothing for all
                retry = Action { loadInitial(params, callback) }
                initialState.postValue(NetworkState.FAILED(it.message))
                Log.i("errorF", it.message ?: "")
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        //nothing to do
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MagicCardEntity>) {
        networkState.postValue(NetworkState.LOADING)
        api.listCards(params.key, params.requestedLoadSize)
            .map {cardsResponses ->
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
                callback.onResult(it, params.key+1)
                networkState.postValue(NetworkState.LOADED)
            }, onError = {
                retry = Action { loadAfter(params, callback) }
                Log.i("errorF", it.message ?: "")
                networkState.postValue(NetworkState.FAILED(it.message))
                //TODO nothing for all
            })
    }
}