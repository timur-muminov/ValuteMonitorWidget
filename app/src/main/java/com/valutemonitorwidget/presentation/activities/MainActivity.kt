package com.valutemonitorwidget.presentation.activities

import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.valutemonitorwidget.R
import com.valutemonitorwidget.presentation.fragments.CurrencyListAppWidgetConfigFragment
import com.valutemonitorwidget.presentation.fragments.MainFragment
import androidx.fragment.app.Fragment
import com.valutemonitorwidget.presentation.appwidget.CurrencyAppWidgetProvider
import com.valutemonitorwidget.presentation.appwidget.CurrencyListAppWidgetProvider
import com.valutemonitorwidget.presentation.fragments.CurrencyAppWidgetConfigFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, null).commit()
            checkIntent(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        checkIntent(intent)
    }

    private fun checkIntent(intent: Intent) {

        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_CONFIGURE) {
            intent.extras?.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)?.let {
                when(AppWidgetManager.getInstance(this).getAppWidgetInfo(it).provider.className){
                    CurrencyAppWidgetProvider::class.java.name -> navigateToFragment(CurrencyAppWidgetConfigFragment::class.java)
                    CurrencyListAppWidgetProvider::class.java.name -> navigateToFragment(CurrencyListAppWidgetConfigFragment::class.java)
                }
            }
        }
    }

    private fun navigateToFragment(clazz: Class<out Fragment>) {

        supportFragmentManager.beginTransaction().replace(
            R.id.container, clazz,
            bundleOf("id" to intent.extras?.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID))
        ).commit()
    }


}