package com.capstone.nempatin.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.nempatin.data.dummy.PropertyDataGenerator
import com.capstone.nempatin.domain.Property

class PropertyPagingSource : PagingSource<Int, Property>() {

        private val dataGenerator = PropertyDataGenerator()

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
            return try {
                val nextPageNumber = params.key ?: 1
                val response = dataGenerator.createDummyPropertyList()
                LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                    nextKey = nextPageNumber + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Property>): Int? {
            return state.anchorPosition
        }
    }
