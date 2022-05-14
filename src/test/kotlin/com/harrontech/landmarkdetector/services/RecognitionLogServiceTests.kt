package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.domains.models.log.ProbabilityModel
import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.common.CoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.log.BugReportRequest
import com.harrontech.landmarkdetector.domains.requests.log.CameraParametersRequest
import com.harrontech.landmarkdetector.domains.requests.log.OrientationRequest
import com.harrontech.landmarkdetector.domains.requests.log.ScreenClickCoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.log.UserDataRequest
import com.harrontech.landmarkdetector.repositories.RecognizerLogRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
@SpringBootTest
class RecognitionLogServiceTests {
    @Autowired
    lateinit var cityObjectService: CityObjectService

    @Autowired
    lateinit var recognitionLogService: RecognitionLogService

    @Autowired
    lateinit var repository: RecognizerLogRepository

    @Test
    fun `saves log`() {

        var recognitionRequest = RecognitionRequest(
            image = ByteArray(0),
            deviceData = UserDataRequest(
                coordinates = CoordinatesRequest(1.0f, 1.0f),
                cameraParameters = CameraParametersRequest(
                    verticalFOV = 0.0,
                    horizontalFOV = 0.0
                ),
                clickCoordinates = ScreenClickCoordinatesRequest(
                    x = 0.0,
                    y = 0.0
                ),
                orientation = OrientationRequest(
                    azimuth = 0.0,
                    roll = 0.0,
                    pitch = 0.0
                )
            )
        )

        var probability = ProbabilityModel(
            id = UUID.randomUUID().toString(),
            areaProbs = null,
            objectProbs = null,
            recognizedObjectID = "response"
        )

        recognitionLogService.initLog(recognitionRequest, probability)
        assertThat(repository.findAll().size, `is`(1))
    }

    @Test
    fun `reports bug`() {
        var recognitionRequest = RecognitionRequest(
            image = ByteArray(0),
            deviceData = UserDataRequest(
                coordinates = CoordinatesRequest(1.0f, 1.0f),
                cameraParameters = CameraParametersRequest(
                    verticalFOV = 0.0,
                    horizontalFOV = 0.0
                ),
                clickCoordinates = ScreenClickCoordinatesRequest(
                    x = 0.0,
                    y = 0.0
                ),
                orientation = OrientationRequest(
                    azimuth = 0.0,
                    roll = 0.0,
                    pitch = 0.0
                )
            )
        )

        var probability = ProbabilityModel(
            id = UUID.randomUUID().toString(),
            areaProbs = null,
            objectProbs = null,
            recognizedObjectID = "response"
        )

        var token = recognitionLogService.initLog(recognitionRequest, probability).id

        var bugReportRequest = BugReportRequest(
            title = "Bugas",
            type = "Klaida",
            content = "Neatpazintas",
            recognitionToken = "3a17aaef-acfb-4d72-a19d-84df1f955ad2"
        )

        recognitionLogService.reportBug(bugReportRequest)
        assertThat(repository.findAll().size, `is`(1))
    }
}
