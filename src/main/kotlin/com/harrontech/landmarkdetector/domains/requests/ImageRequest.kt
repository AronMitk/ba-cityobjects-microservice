package com.harrontech.landmarkdetector.domains.requests

import com.harrontech.landmarkdetector.domains.requests.log.OrientationRequest
import com.harrontech.landmarkdetector.domains.requests.log.ScreenClickCoordinatesRequest

data class ImageRequest(
    var image: ByteArray,
    var clickCoordinates: ScreenClickCoordinatesRequest? = null,
    var orientation: OrientationRequest? = null
)