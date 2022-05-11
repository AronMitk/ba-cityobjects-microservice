package com.harrontech.landmarkdetector.services.recognition.layers

import com.harrontech.landmarkdetector.domains.models.common.CoordinatesModel
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.hamcrest.CoreMatchers.`is` as Is

@SpringBootTest
class AreaRecognitionLayerTest {

    @Autowired
    lateinit var areaRecognitionLayer: AreaRecognitionLayer

    @TestFactory
    fun `recognizes locations by coordinates`(): List<DynamicTest> {
        val map = emptyMap<CoordinatesModel, Int>().toMutableMap()
        map[CoordinatesModel(52.68356993943418F, 26.286376138759007F)] = 0
        map[CoordinatesModel(54.68807085213885F, 25.280102586198435F)] = 1
        map[CoordinatesModel(54.68616132720848F, 25.289861257636F)] = 2
        map[CoordinatesModel(54.686859638191265F, 25.214375136482236F)] = 3
        map[CoordinatesModel(54.679208130203314F, 25.27771470929096F)] = 4
        map[CoordinatesModel(54.683291663438176F, 25.29368135211702F)] = 5
        map[CoordinatesModel(54.67420672744686F, 25.289511121105342F)] = 6
        map[CoordinatesModel(54.67883843253047F, 25.287178925686876F)] = 7
        map[CoordinatesModel(54.68356993943418F, 25.286376138759007F)] = 8

        return map.map { (coordinates, location) ->
            dynamicTest(
                "given \"$coordinates\", after passing it to ML, it should return $location"
            ) {
                var probs = areaRecognitionLayer.getProbabilities(coordinates.latitude, coordinates.longitude)
                assertThat(areaRecognitionLayer.getResult(probs)?.key, Is(location.toString()))
            }
        }
    }
}
