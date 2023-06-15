package com.capstone.nempatin.data

import com.capstone.nempatin.data.response.property.PropertyResponse
import com.capstone.nempatin.domain.Property

fun mapResponseToDomain(propertyResponse: PropertyResponse): Property {
    return Property(
        name = propertyResponse.name,
        city = propertyResponse.city,
        latitude = propertyResponse.latitude,
        longitude = propertyResponse.longitude,
        price = propertyResponse.price,
        buildingArea = propertyResponse.buildingArea,
        landArea = propertyResponse.landArea,
        bedrooms = propertyResponse.bedrooms,
        bathrooms = propertyResponse.bathrooms,
        garage = propertyResponse.garage,
        certificate = propertyResponse.certificate,
        phoneNumber = propertyResponse.phoneNumber,
        timestamp = System.currentTimeMillis()
    )
}