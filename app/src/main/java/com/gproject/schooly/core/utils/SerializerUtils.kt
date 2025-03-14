package com.gproject.schooly.core.utils

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SerializerUtils {

    inline fun <reified T> decodeFromString(value: String): T? {
        return try {
            Json.decodeFromString(value)
        } catch (t: SerializationException) {
            null
        }
    }

    inline fun <reified T> encodeToString(value: T): String? {
        return try {
            return Json.encodeToString(value)
        } catch (t: SerializationException) {
            null
        }
    }
}