package com.harrontech.landmarkdetector.services.recognition.layers

import com.harrontech.landmarkdetector.config.TfModelsConfig
import org.springframework.stereotype.Service
import org.tensorflow.SavedModelBundle
import org.tensorflow.Tensor

// https://blog.mimacom.com/getting-started-tensorflow-spring/
// //saved_model_cli show --dir '.' --all

@Service
class AreaRecognitionLayer(config: TfModelsConfig) : RecognitionLayer() {

    private val model = SavedModelBundle
        .load(config.area.tfModel, "serve")
        .session()

    fun getProbabilities(latitude: Float, longitude: Float): Map<String, Float> {
        val result = model.runner()
            .feed("serving_default_latitude", Tensor.create(latitude))
            .feed("serving_default_longitude", Tensor.create(longitude))
            .fetch("StatefulPartitionedCall")
            .run()[0]

        val resultMatrix = Array(1) { FloatArray(9) }
        result.copyTo(resultMatrix)

        return resultMatrix[0].mapIndexed { idx, it ->
            idx.toString() to it
        }.toMap()
    }

    override fun getLayerName() = RecognitionLayerName.LOCATION_RECOGNITION
}

