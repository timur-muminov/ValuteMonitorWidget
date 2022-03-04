package com.valutemonitorwidget.presentation.fragments

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.valutemonitorwidget.R

class CurrencyAppWidgetConfigFragment: Fragment(R.layout.currency_appwidget_config_fragment) {

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val id = requireArguments().getInt("id")

        requireView().findViewById<Button>(R.id.configure).setOnClickListener {
            requireActivity().setResult(
                Activity.RESULT_OK, Intent().putExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    id
                )
            )
            requireActivity().finish()
        }
    }
}