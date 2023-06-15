package com.capstone.nempatin.data.response

import com.google.gson.annotations.SerializedName

data class PlaceDetailsResponse(
    @SerializedName("result")
    val result: Result
)

data class Result(
    @SerializedName("photos")
    val photos: List<Photo>?
)

data class Photo(
    @SerializedName("photo_reference")
    val photoReference: String
)



