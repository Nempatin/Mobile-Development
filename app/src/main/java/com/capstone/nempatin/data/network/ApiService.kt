package com.capstone.nempatin.data.network

import com.capstone.nempatin.data.response.property.PropertyResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface ApiService {

    @GET("getAll")
    suspend fun getAllProperties(): Response<List<PropertyResponse>>

    @GET("getAll")
    suspend fun getProperties(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<PropertyResponse>>

}
