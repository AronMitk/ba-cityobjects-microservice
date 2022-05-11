package com.harrontech.landmarkdetector.domains

import com.harrontech.landmarkdetector.models.cityobject.CityObjectModel
import com.harrontech.landmarkdetector.models.CoordinatesEntity
import com.harrontech.landmarkdetector.models.cityobject.AddressModel
import com.harrontech.landmarkdetector.models.cityobject.CityObjectTypeModel
import com.harrontech.landmarkdetector.models.common.CoordinatesModel

data class CityObjectRequest (
    val title: String,
    val type: CityObjectTypeRequest,
    val description: String,
    val beforeYouGo: String,
    val address : AddressRequest,
    val coordinates: CoordinatesRequest
    )

data class CoordinatesRequest(
    val latitude : Double,
    val longitude : Double
)

data class AddressRequest(
    val address: String,
    val city: String,
    val country: String
)

enum class CityObjectTypeRequest{
    MONUMENT,
    CHURCH,
    MUSEUM
}

fun CityObjectRequest.mapTo() : CityObjectModel {
    return CityObjectModel(
        title = this.title,
        type = this.type.mapTo(),
        description = this.description,
        beforeYouGo = this.beforeYouGo,
        address = this.address.mapTo(),
        coordinates = this.coordinates.mapTo()
    )
}

fun CoordinatesRequest.mapTo() : CoordinatesModel {
    return CoordinatesModel(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun AddressRequest.mapTo() : AddressModel {
    return AddressModel(
        address = this.address,
        city = this.city,
        country = this.country
    )
}

fun CityObjectTypeRequest.mapTo() : CityObjectTypeModel {
    return CityObjectTypeModel.valueOf(this.name)
}