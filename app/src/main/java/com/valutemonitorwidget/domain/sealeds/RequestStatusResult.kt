package com.valutemonitorwidget.domain.sealeds

import com.valutemonitorwidget.domain.sealeds.enums.RequestFailureStatus

sealed class RequestStatusResult <out DATA> {
    class Success<out DATA>(val response: DATA) : RequestStatusResult<DATA>()
    class Failure(private val status: RequestFailureStatus) : RequestStatusResult<Nothing>()
}
