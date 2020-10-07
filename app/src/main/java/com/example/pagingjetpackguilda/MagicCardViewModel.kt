package com.example.pagingjetpackguilda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MagicCardViewModel: ViewModel() {

    private val _showLoadingView = MutableLiveData<Boolean>()
    private val _listCards = MutableLiveData<List<MagicCardEntity>>()
    private val _errorLoading = MutableLiveData<String>()

    val listCards: LiveData<List<MagicCardEntity>> = _listCards
    val errorLoading: LiveData<String> = _errorLoading
    val showLoadingView: LiveData<Boolean> = _showLoadingView

    fun fetchMagicCards() {
//        val repository = Repository()
//        _showLoadingView.value = true
//
//        repository
//            .fetchData()
//            .map {
//                it.cards.map { magicCardResponse ->
//                    MagicCardEntity(
//                        magicCardResponse.name,
//                        magicCardResponse.type,
//                        magicCardResponse.rarity
//                    )
//                }
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(::success, ::error)
    }

    private fun success(magicCards: List<MagicCardEntity>) {
        _showLoadingView.value = false
        _listCards.value = magicCards
    }

    private fun error(throwable: Throwable) {
        _showLoadingView.value = false
        _errorLoading.value = throwable.message
    }

}