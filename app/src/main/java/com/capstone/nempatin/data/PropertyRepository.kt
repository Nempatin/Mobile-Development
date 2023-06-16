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
                pageSize = 25, // number of items fetched per request from API
                prefetchDistance = 5, // how far from the edge of the loaded content an access must be to trigger further loading
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PropertyPagingSource(apiService) }
        ).flow
    }
}

