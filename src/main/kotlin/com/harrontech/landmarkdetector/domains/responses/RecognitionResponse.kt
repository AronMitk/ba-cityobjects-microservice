package com.harrontech.landmarkdetector.domains.responses

data class RecognitionResponse(
    var token: String? = "",
    var recognizedObjectID: String? = null
)