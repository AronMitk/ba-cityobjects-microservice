package com.harrontech.landmarkdetector.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import kotlin.properties.Delegates

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "recognizer")
class TfModelsConfig {
    var landmark: LandmarkConfig = LandmarkConfig()

    var area: AreaConfig = AreaConfig()

    inner class AreaConfig {
        var tfModel: String = ""
        var areas: List<String> = ArrayList()
    }

    class LandmarkConfig {
        lateinit var url: String
        var mock by Delegates.notNull<Boolean>()
    }
}
