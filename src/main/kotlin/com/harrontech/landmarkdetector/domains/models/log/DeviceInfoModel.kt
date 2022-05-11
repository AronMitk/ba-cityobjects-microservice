package com.harrontech.landmarkdetector.domains.models.log

import com.harrontech.landmarkdetector.domains.models.common.CoordinatesModel

data class DeviceInfoModel(
    var coordinates: CoordinatesModel,
    var orientation: OrientationModel,
    var clickCoordinates: ScreenClickCoordinatesModel,
    var cameraParameters: CameraParametersModel
)