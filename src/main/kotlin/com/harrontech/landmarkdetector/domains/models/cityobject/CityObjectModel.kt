package com.harrontech.landmarkdetector.domains.models.cityobject

import com.harrontech.landmarkdetector.domains.models.common.CoordinatesModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("city_object")
data class CityObjectModel(
    @Id
    val id: String = UUID.randomUUID().toString(),
    var externalId: String,
    var title: String,
    var type: CityObjectTypeModel,
    var description: String,
    var beforeYouGo: String,
    var coordinates: CoordinatesModel,
    var imageUrl: String
)
