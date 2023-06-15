package com.capstone.nempatin.data.response.geocoding

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    @SerializedName("results") val results: List<GeocodingResult>
)

data class GeocodingResult(
    @SerializedName("place_id") val placeId: String
)