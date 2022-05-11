package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.config.TfModelsConfig
import com.harrontech.landmarkdetector.services.recognition.layers.AreaRecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.ObjectRecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.RecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.RecognitionLayerName
import org.springframework.stereotype.Service

@Service
class ObjectRecognitionService(
    val layers: List<RecognitionLayer>,
    val config: TfModelsConfig
) {
    fun recognizeObject(latitude: Float, longitude: Float, image: ByteArray): String? {

        val location = getLayerByName(RecognitionLayerName.LOCATION_RECOGNITION) as AreaRecognitionLayer
        var results = location.getResult(location.getProbabilities(latitude, longitude))

        if (results == null || results.key.toInt() == 0)
            throw NoObjectException()

        var area = config.area.areas[results.key.toInt() - 1]

        val recognition = getLayerByName(RecognitionLayerName.OBJECT_RECOGNITION) as ObjectRecognitionLayer
        var recognitionResults = recognition.getProbabilities(area, image)

        return recognition.getResult(recognitionResults!!)?.key
    }

    fun getLayerByName(name: RecognitionLayerName) = layers.find { it.getLayerName() == name }
}
