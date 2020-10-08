package com.example.pagingjetpackguilda.paging2

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingjetpackguilda.MagicCardEntity

class PagingDataSourceFactory: DataSource.Factory<Int, MagicCardEntity>() {

    val dataSourceLiveData = MutableLiveData<PagingDataSource>()

    override fun create(): DataSource<Int, MagicCardEntity> {
        val dataSource = PagingDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

}