package com.harrontech.landmarkdetector.domains.requests.log

import com.harrontech.landmarkdetector.domains.models.log.CameraParametersModel

data class CameraParametersRequest(
    var verticalFOV: Double = 90.0,
    var horizontalFOV: Double = 90.0
)

fun CameraParametersRequest?.mapTo(): CameraParametersModel? {
    if (this == null)
        return null

    return CameraParametersModel(
        verticalFOV = this.verticalFOV,
        horizontalFOV = this.horizontalFOV
    )
}
