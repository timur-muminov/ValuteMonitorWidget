package com.valutemonitorwidget.presentation.service.app_widget_remote_views

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.valutemonitorwidget.R
import com.valutemonitorwidget.domain.sealeds.RequestStatusResult
import com.valutemonitorwidget.domain.interfaces.CurrencyRepository
import com.valutemonitorwidget.domain.models.Currency
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

class WidgetRemoteViewFactory(
    appWidgetId: Int,
    private val context: Context,
    private val currencyRepository: CurrencyRepository
) : RemoteViewsService.RemoteViewsFactory {

    private var items: List<Currency> = emptyList()

    override fun onCreate() {}

    override fun onDataSetChanged() {
        runBlocking {
            items = when(val result = currencyRepository.getCurrencies().last()){
                is RequestStatusResult.Failure -> emptyList()
                is RequestStatusResult.Success -> {
                    Log.e("flag "," " + result.response)
                    result.response
                }
            }
        }
    }

    override fun onDestroy() {
        items = emptyList()
    }

    override fun getCount(): Int = items.size

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.appwidget_item)
        val item = items[position]
        remoteViews.setTextViewText(R.id.name,item.charCode)
        remoteViews.setTextViewText(R.id.value, item.value.toInt().toString())
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false

}