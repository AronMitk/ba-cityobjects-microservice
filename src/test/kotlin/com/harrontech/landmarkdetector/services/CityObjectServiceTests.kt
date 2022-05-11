package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.domains.requests.cityObject.CityObjectRequest
import com.harrontech.landmarkdetector.domains.requests.cityObject.CityObjectTypeRequest
import com.harrontech.landmarkdetector.domains.requests.common.CoordinatesRequest
import com.harrontech.landmarkdetector.repositories.CityObjectRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class CityObjectServiceTests {
    @Autowired
    lateinit var cityObjectService: CityObjectService

    @Autowired
    lateinit var repository: CityObjectRepository

    @Test
    fun `saves city object from request`() {
        var request = CityObjectRequest(
            externalId = "LTVLN1_1",
            title = "Testas",
            type = CityObjectTypeRequest.CHURCH,
            description = "Description text",
            beforeYouGo = "Before you go text",
            coordinates = CoordinatesRequest(1.0f, 2.0f)
        )

        var resultId = cityObjectService.saveCityObject(request).id

        assertThat(repository.findAll().size, `is`(1))

        var result = repository.findById(resultId).get()

        assertThat(result.title, `is`(request.title))
        assertThat(result.description, `is`(request.description))
        assertThat(result.beforeYouGo, `is`(request.beforeYouGo))
        assertThat(result.coordinates.latitude, `is`(request.coordinates.latitude))
        assertThat(result.coordinates.longitude, `is`(request.coordinates.longitude))
        assertThat(result.type.name, `is`(request.type.name))
    }

    @Test
    fun `updates city object from request`() {
        var request = CityObjectRequest(
            externalId = "LTVLN1_1",
            title = "Testas",
            type = CityObjectTypeRequest.CHURCH,
            description = "Description text",
            beforeYouGo = "Before you go text",
            coordinates = CoordinatesRequest(1.0f, 2.0f)
        )
        var id = cityObjectService.saveCityObject(request).id

        request.title = "Updated request"

        var resultId = cityObjectService.updateCityObject(id, request).id

        assertThat(repository.findAll().size, `is`(1))

        var result = repository.findById(resultId).get()

        assertThat(result.title, `is`(request.title))
        assertThat(result.description, `is`(request.description))
        assertThat(result.beforeYouGo, `is`(request.beforeYouGo))
        assertThat(result.coordinates.latitude, `is`(request.coordinates.latitude))
        assertThat(result.coordinates.longitude, `is`(request.coordinates.longitude))
        assertThat(result.type.name, `is`(request.type.name))
    }

    @Test
    fun `removes city object from request`() {
        var request = CityObjectRequest(
            externalId = "LTVLN1_1",
            title = "Testas",
            type = CityObjectTypeRequest.CHURCH,
            description = "Description text",
            beforeYouGo = "Before you go text",
            coordinates = CoordinatesRequest(1.0f, 2.0f)
        )
        var id = cityObjectService.saveCityObject(request).id

        assertThat(repository.findAll().size, `is`(1))

        cityObjectService.deleteCityObject(id)

        assertThat(repository.findAll().size, `is`(0))
    }

    @TestFactory
    fun `finds city object by search`(): List<DynamicTest> {
        cityObjectService.saveCityObject(generateCityObjectRequest("Title 1", CityObjectTypeRequest.CHURCH))
        cityObjectService.saveCityObject(generateCityObjectRequest("Objektas X", CityObjectTypeRequest.CHURCH))
        cityObjectService.saveCityObject(generateCityObjectRequest("YES YES", CityObjectTypeRequest.CHURCH))
        cityObjectService.saveCityObject(generateCityObjectRequest("Katedra 1", CityObjectTypeRequest.CHURCH))

        val map = emptyMap<String, Int>().toMutableMap()
        map["Title 1"] = 1
        map["0"] = 0
        map["1"] = 2

        return map.map { (search, resultNumber) ->
            DynamicTest.dynamicTest(
                "given \"$search\", find $resultNumber records"
            ) {
                var result = cityObjectService.find("Title 1")
                assertThat(result.size, `is`(1))
            }
        }
    }

    fun generateCityObjectRequest(title: String, type: CityObjectTypeRequest): CityObjectRequest {
        return CityObjectRequest(
            externalId = "LTVLN",
            title = title,
            type = type,
            description = "Description text",
            beforeYouGo = "Before you go text",
            coordinates = CoordinatesRequest(1.0f, 2.0f)
        )
    }
}
