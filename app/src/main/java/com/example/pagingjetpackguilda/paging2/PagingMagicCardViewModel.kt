package com.example.pagingjetpackguilda.paging2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingjetpackguilda.MagicCardEntity

class PagingMagicCardViewModel: ViewModel() {

    var ordersLiveData: LiveData<PagedList<MagicCardEntity>>

    private val pageSize = 8

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()

        val pagingDataSource = PagingDataSourceFactory()

        ordersLiveData = LivePagedListBuilder(pagingDataSource, config).build()
    }

}