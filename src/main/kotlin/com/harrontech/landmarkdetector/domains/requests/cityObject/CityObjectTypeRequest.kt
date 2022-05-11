package com.harrontech.landmarkdetector.domains.requests.cityObject

import com.harrontech.landmarkdetector.domains.models.cityobject.CityObjectTypeModel

enum class CityObjectTypeRequest {
    MONUMENT,
    CHURCH,
    MUSEUM
}

fun CityObjectTypeRequest.mapTo(): CityObjectTypeModel {
    return CityObjectTypeModel.valueOf(this.name)
}