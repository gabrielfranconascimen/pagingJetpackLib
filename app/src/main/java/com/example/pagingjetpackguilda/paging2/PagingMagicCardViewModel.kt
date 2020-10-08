package com.example.pagingjetpackguilda.paging2

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingjetpackguilda.MagicCardEntity

class PagingMagicCardViewModel : ViewModel() {

    var ordersLiveData: LiveData<PagedList<MagicCardEntity>>
    private val pagingDataSourceFactory = PagingDataSourceFactory()

    private val pageSize = 8

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()

        ordersLiveData = LivePagedListBuilder(pagingDataSourceFactory, config).build()
    }

    fun getInitialState(): LiveData<NetworkState> =
        Transformations.switchMap<PagingDataSource, NetworkState>(
            pagingDataSourceFactory.dataSourceLiveData
        ) { it.initialState }

    fun getNetworkState(): LiveData<NetworkState> =
        Transformations.switchMap<PagingDataSource, NetworkState>(
            pagingDataSourceFactory.dataSourceLiveData
        ) { it.networkState }

    fun tryAgain() {
        pagingDataSourceFactory.dataSourceLiveData.value?.tryAgain()
    }
}