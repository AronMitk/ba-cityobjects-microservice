package com.harrontech.landmarkdetector.controllers

import com.harrontech.landmarkdetector.domains.requests.cityObject.CityObjectRequest
import com.harrontech.landmarkdetector.services.CityObjectService
import com.harrontech.landmarkdetector.services.LoginService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class CityObjectsController(
    val service: CityObjectService,
    val loginService: LoginService
) {

    @GetMapping("city-objects/{id}")
    fun getByID(@PathVariable id: String) = service.getCityObject(id)

    @GetMapping("city-objects:search")
    fun search(@RequestParam text: String) = service.find(text)

    @GetMapping
    fun getAll() = service.getAll()

    @PostMapping
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
