package com.gproject.schooly.core.models

import kotlinx.serialization.Serializable

@Serializable
data class Result<T>(
    val token: String?,
    val data: T?,
    val message: String?,
    val errors: HashMap<String, List<String>>? = null
)