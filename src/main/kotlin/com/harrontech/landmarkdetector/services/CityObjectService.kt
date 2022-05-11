package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.domains.models.cityobject.CityObjectModel
import com.harrontech.landmarkdetector.domains.requests.cityObject.CityObjectRequest
import com.harrontech.landmarkdetector.domains.requests.cityObject.mapTo
import com.harrontech.landmarkdetector.domains.requests.common.mapTo
import com.harrontech.landmarkdetector.repositories.CityObjectRepository
import org.springframework.stereotype.Service

@Service
class CityObjectService(val repository: CityObjectRepository) {

    fun saveCityObject(cityObject: CityObjectRequest): CityObjectModel {
        return repository.save(cityObject.mapTo())
    }

    fun getCityObject(id: String) = repository.findById(id)

    fun getAll() = repository.findAll()

    fun find(text: String): List<CityObjectModel> {
        return repository.findAll()
    }

    fun updateCityObject(id: String, request: CityObjectRequest): CityObjectModel {
        var obj = repository.findById(id).get()

        obj = obj.apply {
            title = request.title
            description = request.description
            beforeYouGo = request.beforeYouGo
            type = request.type.mapTo()
            coordinates = request.coordinates.mapTo()
        }

        return repository.save(obj)
    }

    fun deleteCityObject(id: String) {
        repository.deleteById(id)
    }
}
