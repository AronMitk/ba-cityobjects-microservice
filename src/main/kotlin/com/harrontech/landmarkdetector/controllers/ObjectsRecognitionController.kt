package com.harrontech.landmarkdetector.controllers

import com.harrontech.landmarkdetector.config.TfModelsConfig
import com.harrontech.landmarkdetector.domains.models.log.bug.BugReportModel
import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.log.BugReportRequest
import com.harrontech.landmarkdetector.domains.responses.RecognitionResponse
import com.harrontech.landmarkdetector.services.recognition.NoObjectException
import com.harrontech.landmarkdetector.services.RecognitionLogService
import com.harrontech.landmarkdetector.services.recognition.ObjectRecognitionService
import kotlinx.datetime.LocalDateTime
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class ObjectsRecognitionController(
    var recognitionService: ObjectRecognitionService,
    var recognitionLogService: RecognitionLogService
) {

    @PostMapping("api/landmarks:recognize")
    fun recognizeObject(@RequestBody request: RecognitionRequest): RecognitionResponse {
        var result = try {
            val coordinates = request.deviceData.coordinates
            recognitionService.recognizeObject(coordinates.latitude, coordinates.longitude, request.image)
        } catch (e: NoObjectException) {
            e.probs
        }

        var log = recognitionLogService.initLog(request, result)
        return RecognitionResponse(
            token = log.id,
            recognizedObjectID = log.recognizedObjectID
        )
    }

    @PostMapping("api/landmarks:report-bug")
    fun reportBug(@RequestBody bugReport: BugReportRequest): BugReportModel? {
        return recognitionLogService.reportBug(bugReport)?.bugReport
    }
}
