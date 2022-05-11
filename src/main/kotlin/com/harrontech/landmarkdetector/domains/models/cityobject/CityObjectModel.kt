package com.harrontech.landmarkdetector.domains.models.cityobject

import com.harrontech.landmarkdetector.domains.models.common.CoordinatesModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("city_object")
data class CityObjectModel(
    @Id
    val id: String = "",
    var externalId: String,
    var title: String,
    var type: CityObjectTypeModel,
    var description: String,
    var beforeYouGo: String,
    var coordinates: CoordinatesModel
)
