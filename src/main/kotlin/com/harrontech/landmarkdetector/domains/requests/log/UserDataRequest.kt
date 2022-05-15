package com.harrontech.landmarkdetector.domains.requests.log

import com.harrontech.landmarkdetector.domains.models.log.DeviceInfoModel
import com.harrontech.landmarkdetector.domains.requests.common.CoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.common.mapTo

data class UserDataRequest(
    var coordinates: CoordinatesRequest,
    var orientation: OrientationRequest? = null,
    var clickCoordinates: ScreenClickCoordinatesRequest? = null,
    var cameraParameters: CameraParametersRequest? = null
)

fun UserDataRequest.mapTo(): DeviceInfoModel {
    return DeviceInfoModel(
        coordinates = this.coordinates.mapTo(),
        orientation = this.orientation.mapTo(),
        clickCoordinates = this.clickCoordinates.mapTo(),
        cameraParameters = this.cameraParameters.mapTo()
    )
}