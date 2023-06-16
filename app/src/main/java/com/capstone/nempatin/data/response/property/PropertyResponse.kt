package com.capstone.nempatin.data.response.property

import com.google.gson.annotations.SerializedName

data class PropertyResponse(
    @SerializedName("FOTO") val photo: String,
    @SerializedName("NAMA") val name: String,
    @SerializedName("KOTA") val city: String,
    @SerializedName("LAT") val latitude: Double,
    @SerializedName("LONG") val longitude: Double,
    @SerializedName("HARGA") val price: Long,
    @SerializedName("ID") val id :Int,
    @SerializedName("LB") val buildingArea: Int,
    @SerializedName("LT") val landArea: Int,
    @SerializedName("KT") val bedrooms: Int,
    @SerializedName("KM") val bathrooms: Int,
    @SerializedName("GRS") val garage: String,
    @SerializedName("SERTIF") val certificate: String,
    @SerializedName("NOMOR") val phoneNumber: Long
)