package com.harrontech.landmarkdetector.domains.requests

import com.harrontech.landmarkdetector.domains.requests.log.UserDataRequest

data class RecognitionRequest(
    var deviceData: UserDataRequest,
    var image: ByteArray
)

