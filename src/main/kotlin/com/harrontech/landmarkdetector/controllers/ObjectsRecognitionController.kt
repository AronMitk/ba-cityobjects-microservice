package com.harrontech.landmarkdetector.controllers

import com.harrontech.landmarkdetector.config.TfModelsConfig
import com.harrontech.landmarkdetector.domains.requests.RecognitionRequest
import com.harrontech.landmarkdetector.domains.requests.log.BugReportRequest
import com.harrontech.landmarkdetector.domains.responses.RecognitionResponse
import com.harrontech.landmarkdetector.services.NoObjectException
import com.harrontech.landmarkdetector.services.ObjectRecognitionService
import com.harrontech.landmarkdetector.services.RecognitionLogService
import kotlinx.datetime.LocalDateTime
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class ObjectsRecognitionController(
    var tfModelsConfig: TfModelsConfig,
    var recognitionService: ObjectRecognitionService,
    var recognitionLogService: RecognitionLogService
) {

    @PostMapping("api/landmarks:recognize")
    fun recognizeObject(@RequestBody request: RecognitionRequest): RecognitionResponse {
        val coordinates = request.deviceData.coordinates

        var result = try {
            recognitionService.recognizeObject(coordinates.latitude, coordinates.longitude, request.image).toString()
        } catch (e: NoObjectException) {
            null
        }

        var log = recognitionLogService.initLog(request)
        log.recognizedObjectID = result
        recognitionLogService.updateLog(log)
        return RecognitionResponse(
            token = log.id,
            recognizedObjectID = log.recognizedObjectID,
            errors = null
        )
    }

    @PostMapping("api/landmarks:report-bug")
    fun reportBug(@RequestBody bugReport: BugReportRequest): String {
        return recognitionLogService.reportBug(bugReport).id
    }

    @DeleteMapping("api/landmarks:delete")
    fun deleteLogs(
        @RequestParam(required = false) fromDate: LocalDateTime? = null,
        @RequestParam(required = false) toDate: LocalDateTime? = null
    ): Boolean {
        return recognitionLogService.delete(fromDate, toDate)
    }
}
