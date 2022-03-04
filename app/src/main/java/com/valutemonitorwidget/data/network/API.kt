package com.valutemonitorwidget.data.network

import com.valutemonitorwidget.data.repository.models.DailyCurrenciesResponse
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET("/daily_json.js")
    suspend fun getCurrencies() : Response<DailyCurrenciesResponse>
}