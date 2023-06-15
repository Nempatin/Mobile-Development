package com.capstone.nempatin.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfigPlaces {
    private const val BASE_URL = "https://maps.googleapis.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: GooglePlacesService = retrofit.create(GooglePlacesService::class.java)
}
