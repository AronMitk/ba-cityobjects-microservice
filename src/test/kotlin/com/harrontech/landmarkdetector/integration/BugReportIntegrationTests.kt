package com.harrontech.landmarkdetector.integration

import com.harrontech.landmarkdetector.domains.responses.RecognitionResponse
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Testcontainers
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BugReportIntegrationTests {

    @LocalServerPort
    private val port = 0

    var restTemplate = TestRestTemplate()

    @Test
    fun `recognizes LTVLN1_12`() {
        val response = restTemplate.exchange(createURLWithPort("/api/city-objects"), HttpMethod.POST, null, RecognitionResponse::class.java)

        MatcherAssert.assertThat(response.body?.recognizedObjectID, Matchers.`is`("LTVLN1_12"))
    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}