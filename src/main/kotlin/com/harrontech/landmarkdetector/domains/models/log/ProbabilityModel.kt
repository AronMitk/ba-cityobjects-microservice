package com.harrontech.landmarkdetector.domains.models.log

data class ProbabilityModel(
    var id: String,
    var areaProbs: Map<String, Float>?,
    var objectProbs: Map<String, Float>?,
    var recognizedObjectID: String?
)