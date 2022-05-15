package com.harrontech.landmarkdetector.controllers

import com.harrontech.landmarkdetector.domains.requests.cityObject.CityObjectRequest
import com.harrontech.landmarkdetector.services.CityObjectService
import com.harrontech.landmarkdetector.services.LoginService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class CityObjectsController(
    val service: CityObjectService,
    val loginService: LoginService
) {

    @GetMapping("city-objects/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByID(@PathVariable id: String) = service.getCityObject(id)

    @GetMapping("city-objects:search", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@RequestParam text: String) = service.find(text)

    @GetMapping("city-objects", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll() = service.getAll()

    @PostMapping("city-objects")
    fun saveCityObject(
        @RequestHeader username: String,
        @RequestHeader password: String,
        @RequestBody request: CityObjectRequest
    ) {
        loginService.login(username, password)
        service.saveCityObject(request)
    }

    @PutMapping("city-objects/{id}")
    fun updateCityObject(
        @RequestHeader username: String,
        @RequestHeader password: String,
        @PathVariable id: String,
        @RequestBody request: CityObjectRequest
    ) {
        loginService.login(username, password)
        service.updateCityObject(id, request)
    }

    @DeleteMapping("city-objects/{id}")
    fun deleteCityObject(
        @RequestHeader username: String,
        @RequestHeader password: String,
        @PathVariable id: String
    ) {
        loginService.login(username, password)
        service.deleteCityObject(id)
    }
}
