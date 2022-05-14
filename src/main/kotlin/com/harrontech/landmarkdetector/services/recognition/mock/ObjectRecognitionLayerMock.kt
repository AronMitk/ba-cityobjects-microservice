package com.harrontech.landmarkdetector.services.recognition.mock

class ObjectRecognitionLayerMock() {

    fun recognizeObject(area: String): HashMap<String, Float> {
        return when (area) {
            "LTVLN1" -> getLTVLN1()
            "LTVLN2" -> getLTVLN2()
            "LTVLN3" -> getLTVLN3()
            "LTVLN4" -> getLTVLN4()
            "LTVLN5" -> getLTVLN5()
            "LTVLN6" -> getLTVLN6()
            "LTVLN7" -> getLTVLN7()
            "LTVLN8" -> getLTVLN8()
            else -> return hashMapOf(
                Pair("NO_OBJECT", 1f)
            )
        }
    }

    fun getLTVLN1(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN1_12", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN2(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN2_1", 1f),
            Pair("LTVLN2_2", 1f),
            Pair("LTVLN2_4", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN3(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN3_5", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN4(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN4_3", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN5(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN5_9", 1f),
            Pair("LTVLN5_10", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN6(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN6_8", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN7(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN7_7", 1f),
            Pair("LTVLN7_11", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }

    fun getLTVLN8(): HashMap<String, Float> {
        return hashMapOf(
            Pair("LTVLN8_6", 1f),
            Pair("NO_OBJECT", 1f)
        )
    }
}
