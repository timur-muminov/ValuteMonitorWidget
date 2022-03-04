package com.valutemonitorwidget.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {

    val api: API = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(API::class.java)
}