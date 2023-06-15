package com.capstone.nempatin.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.nempatin.data.network.ApiService
import com.capstone.nempatin.data.response.property.PropertyResponse
import com.capstone.nempatin.domain.Property

import retrofit2.HttpException
import java.io.IOException

class PropertyPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Property>() {

    override fun getRefreshKey(state: PagingState<Int, Property>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = apiService.getProperties(nextPageNumber * params.loadSize, params.loadSize)

            val propertyResponses = response.body()
            if (propertyResponses != null) {
                LoadResult.Page(
                    data = propertyResponses.mapIndexed { index, propertyResponse -> mapResponseToDomain(propertyResponse, nextPageNumber * params.loadSize + index + 1) },
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    nextKey = if (propertyResponses.isNotEmpty()) nextPageNumber + 1 else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }



}
