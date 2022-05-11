package com.harrontech.landmarkdetector.services

import com.harrontech.landmarkdetector.config.AdminConfig
import org.springframework.stereotype.Service
import javax.naming.NoPermissionException

@Service
class LoginService(var auth: AdminConfig) {
    fun login(username: String, password: String): Boolean {
        if (username == auth.username && password == auth.password) return true

        throw NoPermissionException("Auth is not correct")
    }
}
