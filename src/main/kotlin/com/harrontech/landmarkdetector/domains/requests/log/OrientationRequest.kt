package com.harrontech.landmarkdetector.domains.requests.log

import com.harrontech.landmarkdetector.domains.models.log.CameraParametersModel
import com.harrontech.landmarkdetector.domains.models.log.OrientationModel

data class OrientationRequest(
    var azimuth: Double,
    var pitch: Double,
    var roll: Double
)

fun OrientationRequest.mapTo(): OrientationModel {
    return OrientationModel(
        azimuth = this.azimuth,
        pitch = this.pitch,
        roll = this.roll
    )
}
