package com.valutemonitorwidget.presentation.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.valutemonitorwidget.App
import com.valutemonitorwidget.R
import com.valutemonitorwidget.domain.models.Currency
import com.valutemonitorwidget.domain.sealeds.RequestStatusResult
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CurrencyAppWidgetProvider : AppWidgetProvider() {

    private val currencyRepository = App.instance.currencyRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds?.forEach { id ->
            updateWidget(context, appWidgetManager, id)
        }
    }

    private fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, id: Int) = runBlocking {
        launch {

            when (val result = currencyRepository.getCurrencies().last()) {
                is RequestStatusResult.Failure -> Currency(charCode = "Error")
                is RequestStatusResult.Success ->  {
                    val item = result.response.first { it.charCode == "USD" }

                    val remoteViews = RemoteViews(context.packageName, R.layout.currency_widget)
                    remoteViews.setTextViewText(R.id.value2, item.value.toInt().toString())

                    appWidgetManager.updateAppWidget(id, remoteViews)
                    appWidgetManager.notifyAppWidgetViewDataChanged(id, R.id.value2)
                }
            }
        }
    }
}