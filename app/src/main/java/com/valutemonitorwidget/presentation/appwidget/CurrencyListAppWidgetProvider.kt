package com.valutemonitorwidget.presentation.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.valutemonitorwidget.R
import com.valutemonitorwidget.presentation.service.app_widget_remote_views.AppWidgetRemoteViewsService

class CurrencyListAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds?.forEach {
            updateWidget(context, appWidgetManager, it)
        }
    }

    private fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, id: Int) {
        val remoteViews = RemoteViews(context.packageName, R.layout.currency_list_widget)
        remoteViews.setRemoteAdapter(
            R.id.currencyListView,
            Intent(context, AppWidgetRemoteViewsService::class.java).apply {
                putExtra("id", id)
            })
        appWidgetManager.updateAppWidget(id, remoteViews)

        appWidgetManager.notifyAppWidgetViewDataChanged(id, R.id.currencyListView)
    }
}