package com.harrontech.landmarkdetector.domains.models.log

data class BoxModel(
    var topLeft: ViewCoordinatesModel,
    var topRight: ViewCoordinatesModel,
    var bottomRight: ViewCoordinatesModel,
    var bottomLeft: ViewCoordinatesModel
)