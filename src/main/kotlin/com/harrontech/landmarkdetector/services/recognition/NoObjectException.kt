package com.harrontech.landmarkdetector.services.recognition

import com.harrontech.landmarkdetector.domains.models.log.ProbabilityModel

class NoObjectException(var probs: ProbabilityModel) : Exception()