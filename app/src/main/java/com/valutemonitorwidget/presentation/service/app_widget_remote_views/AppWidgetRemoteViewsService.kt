package com.valutemonitorwidget.presentation.service.app_widget_remote_views

import android.content.Intent
import android.widget.RemoteViewsService
import com.valutemonitorwidget.App

class AppWidgetRemoteViewsService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        WidgetRemoteViewFactory(
            intent.extras!!.getInt("id"),
            applicationContext,
            App.instance.currencyRepository
        )
}
