package com.harrontech.landmarkdetector.services.recognition

import com.harrontech.landmarkdetector.config.TfModelsConfig
import com.harrontech.landmarkdetector.domains.models.log.ProbabilityModel
import com.harrontech.landmarkdetector.repositories.CityObjectRepository
import com.harrontech.landmarkdetector.services.recognition.layers.AreaRecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.ObjectRecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.RecognitionLayer
import com.harrontech.landmarkdetector.services.recognition.layers.RecognitionLayerName
import org.springframework.stereotype.Service
import java.util.*

@Service
class ObjectRecognitionService(
    val layers: List<RecognitionLayer>,
    val config: TfModelsConfig,
    val cityObjectRepository: CityObjectRepository
) {
    fun recognizeObject(latitude: Float, longitude: Float, image: ByteArray): ProbabilityModel {

        val location = getLayerByName(RecognitionLayerName.LOCATION_RECOGNITION) as AreaRecognitionLayer
        var regionRecognitionProbs = location.getProbabilities(latitude, longitude)
        var regionResult = location.getResult(regionRecognitionProbs)

        if (regionResult == null || regionResult.key.toInt() == 0)
            throw NoObjectException(
                ProbabilityModel(
                    UUID.randomUUID().toString(),
                    regionRecognitionProbs,
                    null,
                    null
                )
            )

        var area = config.area.areas[regionResult.key.toInt() - 1]

        val recognition = getLayerByName(RecognitionLayerName.OBJECT_RECOGNITION) as ObjectRecognitionLayer
        var recognitionResultsProbs = recognition.getProbabilities(area, image)

        var recognitionResult = recognition.getResult(recognitionResultsProbs)?.key

        if (recognitionResult == "NO_OBJECT" || recognitionResult == null)
            throw NoObjectException(
                ProbabilityModel(
                    UUID.randomUUID().toString(),
                    regionRecognitionProbs,
                    recognitionResultsProbs,
                    recognitionResult
                )
            )

        var resultId: String? = cityObjectRepository.findAll().find { it.externalId == recognitionResult }?.id
            ?: throw NoObjectException(
                ProbabilityModel(
                    UUID.randomUUID().toString(),
                    regionRecognitionProbs,
                    recognitionResultsProbs,
                    recognitionResult
                )
            )

        return ProbabilityModel(
            UUID.randomUUID().toString(),
            regionRecognitionProbs,
            recognitionResultsProbs,
            resultId
        )
    }

    fun getLayerByName(name: RecognitionLayerName) = layers.find { it.getLayerName() == name }
}
