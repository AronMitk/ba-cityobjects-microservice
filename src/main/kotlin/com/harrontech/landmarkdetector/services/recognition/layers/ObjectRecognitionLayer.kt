package com.harrontech.landmarkdetector.services.recognition.layers

import com.harrontech.landmarkdetector.config.TfModelsConfig
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ObjectRecognitionLayer(val config: TfModelsConfig) : RecognitionLayer() {

    fun getProbabilities(area: String, image: ByteArray): HashMap<String, Float>? {
        val responseType: ParameterizedTypeReference<HashMap<String, Float>> = object : ParameterizedTypeReference<HashMap<String, Float>>() {}

        val restTemplate = RestTemplate()
        val fooResourceUrl = config.landmark.url

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = HttpEntity(
            ObjectRecognitionApiRequest(area, image),
            headers
        )

        return restTemplate
            .exchange(
                fooResourceUrl,
                HttpMethod.POST,
                entity,
                responseType
            )
            .body
    }

    override fun getLayerName() = RecognitionLayerName.OBJECT_RECOGNITION

    private class ObjectRecognitionApiRequest(
        var area: String,
        var image: ByteArray
    )
}
