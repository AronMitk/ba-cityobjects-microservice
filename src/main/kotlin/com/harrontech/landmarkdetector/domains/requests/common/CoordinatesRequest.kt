package com.harrontech.landmarkdetector.domains.requests.common

import com.harrontech.landmarkdetector.domains.models.common.CoordinatesModel

data class CoordinatesRequest(
    var latitude: Float,
    var longitude: Float
) {
    fun getDistance(coordinates: CoordinatesRequest): Double {
        return 0.0
    }
    fun bearingTo(coordinates: CoordinatesRequest): Double {
        return 0.0
    }
}

fun CoordinatesRequest.mapTo(): CoordinatesModel {
    return CoordinatesModel(
        this.latitude,
        this.longitude
    )
}
