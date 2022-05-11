package com.harrontech.landmarkdetector.domains

data class UserDataRequest(
    var coordinates : Coordinates,
    var orientation: Orientation,
    var clickCoordinates: ScreenClickCoordinates,
    var cameraParameters: CameraParameters
)
data class Coordinates(
    var latitude : Double,
    var longitude : Double
){
    fun getDistance(coordinates: Coordinates) : Double{
        return 0.0
    }
    fun bearingTo(coordinates: Coordinates) : Double{
        return 0.0
    }
}

data class Orientation(
    var azimuth : Double,
    var pitch : Double,
    var roll : Double
)

data class CameraParameters(
    var verticalFOV : Double = 90.0,
    var horizontalFOV : Double = 90.0
)

data class ScreenClickCoordinates(
    var x : Double,
    var y : Double
)