package com.harrontech.landmarkdetector.domains.requests.log

import com.harrontech.landmarkdetector.domains.models.log.bug.BugReportModel

data class BugReportRequest(
    val title: String,
    val type: String,
    val content: String,
    val recognitionToken: String
)

fun BugReportRequest.mapTo(): BugReportModel {
    return BugReportModel(
        title = this.title,
        type = this.type,
        content = this.content
    )
}