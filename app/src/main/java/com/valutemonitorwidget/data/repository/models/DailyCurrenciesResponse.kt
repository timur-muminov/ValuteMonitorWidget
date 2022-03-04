package com.valutemonitorwidget.data.repository.models

import com.google.gson.annotations.SerializedName

data class DailyCurrenciesResponse(

    @SerializedName("Valute")
    val valute: Map<String, Currency>
)
