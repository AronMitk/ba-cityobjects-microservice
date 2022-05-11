package com.harrontech.landmarkdetector.domains

data class BugReport(
    val title:String,
    val type:String,
    val content:String,
    val recognitionToken: String
)