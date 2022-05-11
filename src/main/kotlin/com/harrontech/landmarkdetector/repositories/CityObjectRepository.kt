package com.harrontech.landmarkdetector.repositories;

import com.harrontech.landmarkdetector.domains.models.cityobject.CityObjectModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CityObjectRepository : MongoRepository<CityObjectModel, String>