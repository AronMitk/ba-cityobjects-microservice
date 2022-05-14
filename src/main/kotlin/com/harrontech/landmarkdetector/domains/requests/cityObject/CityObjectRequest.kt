package com.harrontech.landmarkdetector.domains.requests.cityObject

import com.harrontech.landmarkdetector.domains.models.cityobject.CityObjectModel
import com.harrontech.landmarkdetector.domains.models.cityobject.CityObjectTypeModel
import com.harrontech.landmarkdetector.domains.requests.common.CoordinatesRequest
import com.harrontech.landmarkdetector.domains.requests.common.mapTo

data class CityObjectRequest(
    val externalId: String,
    var title: String,
    val type: CityObjectTypeRequest,
    val description: String,
    val beforeYouGo: String,
    val coordinates: CoordinatesRequest,
    val imageUrl: String
)

fun CityObjectRequest.mapTo(): CityObjectModel {
    return CityObjectModel(
        externalId = this.externalId,
        title = this.title,
        type = this.type.mapTo(),
        description = this.description,
        beforeYouGo = this.beforeYouGo,
        coordinates = this.coordinates.mapTo(),
        imageUrl = this.imageUrl
    )
}