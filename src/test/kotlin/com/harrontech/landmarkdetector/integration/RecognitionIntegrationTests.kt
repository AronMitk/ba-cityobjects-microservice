package com.harrontech.landmarkdetector.integration

import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.common.CoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.log.CameraParametersRequest
import com.harrontech.landmarkdetector.domains.requests.log.OrientationRequest
import com.harrontech.landmarkdetector.domains.requests.log.ScreenClickCoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.log.UserDataRequest
import com.harrontech.landmarkdetector.domains.responses.RecognitionResponse
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import java.io.File

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecognitionIntegrationTests {
    @LocalServerPort
    private val port = 0

    var restTemplate = TestRestTemplate()

    @Test
    fun `recognizes LTVLN1_12`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN1_12.PNG", 54.68807085213885F, 25.280102586198435F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)

        assertThat(response.body?.recognizedObjectID, `is`("LTVLN1_12"))
    }

    @Test
    fun `recognizes NO_OBJECT`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/no_object.jpeg", 54.68807085213885F, 25.280102586198435F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("NO_OBJECT"))
    }

    @Test
    fun `recognizes LTVLN2_1`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN2_1.jpeg", 54.685987124584955F, 25.288288959006845F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN2_1"))
    }

    @Test
    fun `recognizes LTVLN2_2`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN2_2.jpeg", 54.685987124584955F, 25.288288959006845F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN2_2"))
    }

    @Test
    fun `recognizes LTVLN2_4`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN2_4.jpg", 54.685987124584955F, 25.288288959006845F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN2_4"))
    }

    @Test
    fun `recognizes LTVLN3_5`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN3_5.jpeg", 54.68653162574313F, 25.213394876321498F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN3_5"))
    }

    @Test
    fun `recognizes LTVLN4_3`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN4_3.jpeg", 54.679560081368905F, 25.277991057419488F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN4_3"))
    }

    @Test
    fun `recognizes LTVLN5_9`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN5_10.jpeg", 54.68296716474331F, 25.293331745265363F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN5_9"))
    }

    @Test
    fun `recognizes LTVLN5_10`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN5_9.jpeg", 54.68296716474331F, 25.293331745265363F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN5_10"))
    }

    @Test
    fun `recognizes LTVLN6_8`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN6_8.jpeg", 54.674248773762486F, 25.289608987881984F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN6_8"))
    }

    @Test
    fun `recognizes LTVLN7_7`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN7_11.jpeg", 54.678659208731354F, 25.287315168493087F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN7_7"))
    }

    @Test
    fun `recognizes LTVLN7_11`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN7_7.jpeg", 54.678659208731354F, 25.287315168493087F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN7_11"))
    }

    @Test
    fun `recognizes LTVLN8_6`() {
        val entity = initEmptyRequest("/Users/arnasmitkevicius/IdeaProjects/ba-vor-backend/src/test/resources/LTVLN8_6.jpeg", 54.68337486902389F, 25.286154851814743F)
        val response = restTemplate.exchange(createURLWithPort("/api/landmarks:recognize"), HttpMethod.POST, entity, RecognitionResponse::class.java)
        assertThat(response.body?.recognizedObjectID, `is`("LTVLN8_6"))
    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }

    private fun initEmptyRequest(url: String, latitude: Float, longitude: Float): HttpEntity<RecognitionRequest> {
        return HttpEntity<RecognitionRequest>(
            RecognitionRequest(
                image = File(url).readBytes(),
                deviceData = UserDataRequest(
                    coordinates = CoordinatesRequest(latitude, longitude),
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
        )
    }
}
