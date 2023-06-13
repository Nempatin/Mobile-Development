package com.capstone.nempatin.domain

data class Property(
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val price: Long,
    val buildingArea: Int,
    val landArea: Int,
    val bedrooms: Int,
    val bathrooms: Int,
    val garage: String,
    val certificate: String,
    val phoneNumber: Long,
    val timestamp: Long
)

