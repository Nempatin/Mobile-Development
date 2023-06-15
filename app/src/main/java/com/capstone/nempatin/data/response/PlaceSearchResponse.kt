package com.capstone.nempatin.data.response

import com.google.gson.annotations.SerializedName

data class PlaceSearchResponse(
    @SerializedName("results")
    val results: List<PlaceResult>
)

data class PlaceResult(
    @SerializedName("photos")
    val photos: List<Photo>?
)
