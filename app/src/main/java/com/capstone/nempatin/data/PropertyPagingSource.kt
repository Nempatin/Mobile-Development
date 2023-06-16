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
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getProperties(page, params.loadSize)
            // If the response is successful and we got the properties, map them into the domain model.
            if (response.isSuccessful && response.body() != null) {
                val propertyResponses = response.body()!!

                var properties = propertyResponses.map { propertyResponse ->
                    mapResponseToDomain(propertyResponse)
                }

                LoadResult.Page(
                    data = properties,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = if (propertyResponses.isNotEmpty()) page + 1 else null
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




