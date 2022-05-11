package com.harrontech.landmarkdetector.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "admin.auth")
class AdminConfig {
    lateinit var username: String
    lateinit var password: String
}
