package com.gse.test.proyectqrhackaton.model.statusControl

import com.gse.test.proyectqrhackaton.model.EnumApiStatus

sealed class ApiResult<out T>(
    val statusCode: Int? = 500,
    val status: EnumApiStatus = EnumApiStatus.ERROR,
    val data: T? = null,
    val message: String? = null
) {
    data class Success<out R>(val _statusCode: Int, val _data: R) : ApiResult<R>(
        statusCode = _statusCode,
        status = EnumApiStatus.SUCCESS,
        data = _data
    )

    data class Failure(val _statusCode: Int, val exception: Throwable) : ApiResult<Nothing>(
        statusCode = _statusCode,
        status = EnumApiStatus.ERROR,
        message = exception.message
    )
}

