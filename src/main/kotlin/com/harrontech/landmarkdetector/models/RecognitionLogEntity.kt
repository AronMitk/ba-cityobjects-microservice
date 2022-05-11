package com.harrontech.landmarkdetector.models

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("recognition_log")
data class RecognitionLogEntity(
    val id: String,
    val date: Date,
    val images: ImageDataContainerEntity,
    val result: String,
    var isValid: Boolean = true,
    var bugReport: BugReportModel? = null
)

data class BugReportModel(
    val title: String,
    val type: String,
    val content: String
)

data class ImageDataContainerEntity(
    val imageURL: String,
    val userData: DeviceInfoEntity,
    val probabilities: List<ProbabilityEntity>
)

data class ProbabilityEntity(
    var id: String,
    var prob: Double,
    var box: BoxEntity
)

data class BoxEntity(
    var topLeft: ViewCoordinatesEntity,
    var topRight: ViewCoordinatesEntity,
    var bottomRight: ViewCoordinatesEntity,
    var bottomLeft: ViewCoordinatesEntity
)

data class ViewCoordinatesEntity(
    var x: Double,
    var y: Double
)

data class DeviceInfoEntity(
    var coordinates: CoordinatesEntity,
    var orientation: OrientationEntity,
    var clickCoordinates: ScreenClickCoordinatesEntity,
    var cameraParameters: CameraParametersEntity
)
data class CoordinatesEntity(
    var latitude: Double,
    var longitude: Double
)

data class OrientationEntity(
    var azimuth: Double,
    var pitch: Double,
    var roll: Double
)

data class CameraParametersEntity(
    var verticalFOV: Double = 90.0,
    var horizontalFOV: Double = 90.0
)

data class ScreenClickCoordinatesEntity(
    var x: Double,
    var y: Double
)
