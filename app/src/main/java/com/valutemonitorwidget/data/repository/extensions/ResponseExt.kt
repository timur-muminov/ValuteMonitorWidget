package com.valutemonitorwidget.data.repository.extensions

import com.valutemonitorwidget.domain.sealeds.RequestStatusResult
import com.valutemonitorwidget.domain.sealeds.enums.RequestFailureStatus
import retrofit2.Response

fun <DATA,T> Response<DATA>.determineStatus(transform: (DATA) -> T): RequestStatusResult<T> {
    try {
        if (isSuccessful) body()?.let {
            return RequestStatusResult.Success(transform(it))
        }
        return RequestStatusResult.Failure(RequestFailureStatus.SERVER_ERROR)
    } catch (t: Throwable) {
        return RequestStatusResult.Failure(RequestFailureStatus.CLIENT_ERROR)
    }
}