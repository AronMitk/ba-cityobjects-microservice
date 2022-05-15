package com.harrontech.landmarkdetector.domains.models.log

data class ImageDataContainerModel(
    val imageURL: String,
    val userData: DeviceInfoModel?,
    var probabilities: ProbabilityModel?
)