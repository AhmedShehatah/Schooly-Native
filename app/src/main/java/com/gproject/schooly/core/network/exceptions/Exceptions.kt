package com.gproject.schooly.core.network.exceptions


object NetworkException : Exception("Network Exception")

open class GeneralHttpException(
    val validationErrors: HashMap<String, List<String>>?, val errorMsg: String?
) : Exception("General Http Exception")


class UnauthorizedException : Exception("Unauthorized Exception")

class UnknownException(message: String) : Exception(message)