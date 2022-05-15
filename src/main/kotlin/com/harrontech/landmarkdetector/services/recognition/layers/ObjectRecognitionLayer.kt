package com.harrontech.landmarkdetector.services.recognition.layers

import com.harrontech.landmarkdetector.config.TfModelsConfig
import com.harrontech.landmarkdetector.services.recognition.mock.ObjectRecognitionLayerMock
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ObjectRecognitionLayer(val config: TfModelsConfig) : RecognitionLayer() {

    fun getProbabilities(area: String, image: ByteArray): HashMap<String, Float> {
        val responseType: ParameterizedTypeReference<HashMap<String, Float>> =
            object : ParameterizedTypeReference<HashMap<String, Float>>() {}

        val restTemplate = RestTemplate()
        val landmarkRecognitionUrl = config.landmark.url

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = HttpEntity(
            ObjectRecognitionApiRequest(area, image),
            headers
        )

        if (config.landmark.mock) {
            return ObjectRecognitionLayerMock().recognizeObject(area)
        }

        return restTemplate
            .exchange(landmarkRecognitionUrl, HttpMethod.POST, entity, responseType)
            .body ?: hashMapOf(Pair("NO_OBJECT", 1f))
    }

    override fun getLayerName() = RecognitionLayerName.OBJECT_RECOGNITION

    private inner class ObjectRecognitionApiRequest(
        var area: String,
        var image: ByteArray
    )
}
