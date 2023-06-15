package com.capstone.nempatin.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capstone.nempatin.data.network.ApiService
import com.capstone.nempatin.domain.Property

import kotlinx.coroutines.flow.Flow

class PropertyRepository(private val apiService: ApiService) {

    fun fetchProperties(): Flow<PagingData<Property>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PropertyPagingSource(apiService) }
        ).flow
    }
}
