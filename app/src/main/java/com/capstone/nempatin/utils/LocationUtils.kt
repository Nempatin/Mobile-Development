package com.capstone.nempatin.utils

import com.capstone.nempatin.domain.Property

object LocationUtils {
    fun isNearby(
        userLatitude: Double,
        userLongitude: Double,
        propertyLatitude: Double,
        propertyLongitude: Double
    ): Boolean {
        // Calculate the distance between the user's location and the property's location
        val distance = calculateDistance(userLatitude, userLongitude, propertyLatitude, propertyLongitude)

        // Define a threshold distance, e.g., 5 kilometers
        val thresholdDistance = 5000 // meters

        // Return true if the distance is within the threshold, indicating the property is near the user
        return distance <= thresholdDistance
    }

    private fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        // Implement the distance calculation algorithm here
        // You can use the Haversine formula or any other distance calculation algorithm

        // Example implementation using the Haversine formula:
        val earthRadius = 6371 // kilometers

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val distance = earthRadius * c

        return distance * 1000 // Convert to meters
    }
}
