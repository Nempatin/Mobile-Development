package com.capstone.nempatin.data.network

import com.capstone.nempatin.data.response.property.PropertyResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface ApiService {

    @GET("getAll")
    suspend fun getAllProperties(): Response<List<PropertyResponse>>

    @GET("getPropertiesAfterId")
    suspend fun getPropertiesAfterId(
        @Query("lastPropertyId") lastPropertyId: String?,
        @Query("limit") limit: Int
    ): Response<List<PropertyResponse>>
}
