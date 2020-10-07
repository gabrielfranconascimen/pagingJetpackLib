package com.example.pagingjetpackguilda.paging2

import androidx.paging.DataSource
import com.example.pagingjetpackguilda.MagicCardEntity

class PagingDataSourceFactory: DataSource.Factory<Int, MagicCardEntity>() {

    override fun create(): DataSource<Int, MagicCardEntity> {
        return PagingDataSource()
    }

}