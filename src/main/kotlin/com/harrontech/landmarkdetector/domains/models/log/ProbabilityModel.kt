package com.harrontech.landmarkdetector.domains.models.log

data class ProbabilityModel(
    var id: String,
    var prob: Double,
    var box: BoxModel
)