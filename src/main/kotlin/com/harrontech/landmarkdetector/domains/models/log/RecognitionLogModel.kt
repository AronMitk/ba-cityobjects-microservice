package com.harrontech.landmarkdetector.domains.models.log

import com.harrontech.landmarkdetector.domains.models.log.bug.BugReportModel
import kotlinx.datetime.LocalDateTime
import org.springframework.data.mongodb.core.mapping.Document

@Document("recognition_log")
data class RecognitionLogModel(
    val id: String,
    val date: LocalDateTime,
    val images: ImageDataContainerModel,
    var recognizedObjectID: String? = null,
    var isValid: Boolean = true,
    var bugReport: BugReportModel? = null
)