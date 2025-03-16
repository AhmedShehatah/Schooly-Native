package com.gproject.schooly.core.network.exceptions


class NetworkException(message: String?) : Exception(message ?: "Network Exception")
open class GeneralHttpException(
    val validationErrors: HashMap<String, List<String>>?, val errorMsg: String?
) : Exception("General Http Exception")


class UnauthorizedException(errorMessage: String?) :
    Exception(errorMessage ?: "Unauthorized Exception")

class UnknownException(message: String) : Exception(message)