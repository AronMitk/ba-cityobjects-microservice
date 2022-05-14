package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.config.FileStorageConfig
import com.harrontech.landmarkdetector.domains.models.log.ImageDataContainerModel
import com.harrontech.landmarkdetector.domains.models.log.ProbabilityModel
import com.harrontech.landmarkdetector.domains.models.log.RecognitionLogModel
import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.log.BugReportRequest
import com.harrontech.landmarkdetector.domains.requests.log.mapTo
import com.harrontech.landmarkdetector.repositories.RecognizerLogRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service
class RecognitionLogService(val logger: RecognizerLogRepository, var storageConfig: FileStorageConfig) {

    fun saveImage(bytes: ByteArray): Path? {
        val destinationFile: Path = Paths.get(storageConfig.uploadDir, Date().time.toString() + "-" + UUID.randomUUID().toString().replace("-", "") + ".jpg")
        return Files.write(destinationFile, bytes)
    }

    fun initLog(request: RecognitionRequest, probabilities: ProbabilityModel): RecognitionLogModel {
        val log = RecognitionLogModel(
            id = UUID.randomUUID().toString(),
            date = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            images = ImageDataContainerModel(
                imageURL = saveImage(request.image).toString(),
                userData = request.deviceData.mapTo(),
                probabilities = probabilities
            ),
            recognizedObjectID = probabilities.recognizedObjectID,
            isValid = true
        )

        return logger.save(log)
    }

    fun reportBug(bugReportRequest: BugReportRequest): RecognitionLogModel? {
        val recognitionLog = logger.findById(bugReportRequest.recognitionToken)

        return recognitionLog
            .map {
                it.isValid = false
                it.bugReport = bugReportRequest.mapTo()
                logger.save(it)
            }.orElse(null)
    }
}
