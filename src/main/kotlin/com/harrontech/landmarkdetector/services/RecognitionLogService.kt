package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.config.FileStorageConfig
import com.harrontech.landmarkdetector.domains.models.log.ImageDataContainerModel
import com.harrontech.landmarkdetector.domains.models.log.RecognitionLogModel
import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.log.BugReportRequest
import com.harrontech.landmarkdetector.domains.requests.log.mapTo
import com.harrontech.landmarkdetector.repositories.RecognizerLogRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
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
        val destinationFile: Path = Paths.get(storageConfig.uploadDir, Date().time.toString() + "-" + UUID.randomUUID() + ".jpg")
        return Files.write(destinationFile, bytes)
    }

    fun initLog(request: RecognitionRequest): RecognitionLogModel {
        val log = RecognitionLogModel(
            id = UUID.randomUUID().toString(),
            date = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            images = ImageDataContainerModel(
                imageURL = saveImage(request.image).toString(),
                userData = request.deviceData.mapTo(),
                probabilities = null
            ),
            recognizedObjectID = null
        )

        return logger.save(log)
    }

    fun updateLog(recognitionLog: RecognitionLogModel): RecognitionLogModel {
        return logger.save(recognitionLog)
    }

    fun reportBug(bugReportRequest: BugReportRequest): RecognitionLogModel {
        val recognitionLog = logger.findById(bugReportRequest.recognitionToken)

        recognitionLog.ifPresent {
            it.isValid = false
            it.bugReport = bugReportRequest.mapTo()
            logger.save(it)
        }

        return recognitionLog.get()
    }

    fun delete(fromDate: LocalDateTime?, toDate: LocalDateTime?): Boolean {
        logger.findAll().filter {
            dateFilter(it, fromDate, toDate)
        }.forEach {
            logger.deleteById(it.id)
        }

        return true
    }

    fun dateFilter(model: RecognitionLogModel, fromDate: LocalDateTime?, toDate: LocalDateTime?): Boolean {
        if (fromDate != null && toDate != null)
            return model.date >= fromDate && model.date <= toDate

        if (fromDate == null && toDate != null)
            return model.date <= toDate

        if (fromDate != null && toDate == null)
            return model.date >= fromDate

        if (fromDate == null && toDate == null)
            return true

        return false
    }
}
