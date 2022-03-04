package com.valutemonitorwidget

import android.app.Application
import com.valutemonitorwidget.data.network.NetworkConfig
import com.valutemonitorwidget.data.repository.CurrencyRepositoryImpl
import com.valutemonitorwidget.domain.interfaces.CurrencyRepository

class App: Application() {

    val currencyRepository: CurrencyRepository by lazy { CurrencyRepositoryImpl(NetworkConfig().api) }
    //val appWidgetConfigRepository: AppWidgetConfigRepository by lazy { AppWidgetConfigRepository() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: App
    }
}