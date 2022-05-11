package com.harrontech.landmarkdetector.services.recognition.layers

import org.springframework.stereotype.Service

@Service
abstract class RecognitionLayer {
    fun getResult(probabilities: Map<String, Float>?): Map.Entry<String, Float>? {
        if (probabilities == null) return null
        return probabilities.maxByOrNull { it.value }
    }

    abstract fun getLayerName(): RecognitionLayerName
}

enum class RecognitionLayerName {
    LOCATION_RECOGNITION,
    OBJECT_RECOGNITION
}
