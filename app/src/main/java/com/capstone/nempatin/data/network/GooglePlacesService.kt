package com.capstone.nempatin.data.network

import com.capstone.nempatin.data.response.PlaceDetailsResponse
import com.capstone.nempatin.data.response.PlaceSearchResponse
import com.capstone.nempatin.data.response.geocoding.GeocodingResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesService {

    @GET("maps/api/place/nearbysearch/json")
    fun nearbySearch(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("key") key: String
    ): Call<PlaceSearchResponse>


    @GET("maps/api/place/details/json")
    fun getPlaceDetails(
        @Query("place_id") placeId: String,
        @Query("fields") fields: String,
        @Query("key") key: String
    ): Call<PlaceDetailsResponse>

    @GET("maps/api/place/photo")
    fun getPhoto(
        @Query("maxwidth") maxWidth: Int,
        @Query("photoreference") photoReference: String,
        @Query("key") key: String
    ): Call<ResponseBody>

    @GET("maps/api/geocode/json")
    fun getGeocoding(
        @Query("latlng") latlng: String,
        @Query("key") key: String
    ): Call<GeocodingResponse>
}

