package com.harrontech.landmarkdetector.domains.requests.log

import com.harrontech.landmarkdetector.domains.models.log.ScreenClickCoordinatesModel

data class ScreenClickCoordinatesRequest(
    var x: Double,
    var y: Double
)

fun ScreenClickCoordinatesRequest?.mapTo(): ScreenClickCoordinatesModel? {
    if(this == null)
        return null

    return ScreenClickCoordinatesModel(
        x = this.x,
        y = this.y
    )
}