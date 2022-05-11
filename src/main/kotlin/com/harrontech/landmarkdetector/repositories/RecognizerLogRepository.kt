package com.harrontech.landmarkdetector.repositories

import com.harrontech.landmarkdetector.domains.models.log.RecognitionLogModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RecognizerLogRepository : MongoRepository<RecognitionLogModel, String>