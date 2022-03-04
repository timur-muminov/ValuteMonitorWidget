package com.valutemonitorwidget.data.repository

import com.valutemonitorwidget.data.network.API
import com.valutemonitorwidget.data.repository.extensions.determineStatus
import com.valutemonitorwidget.domain.sealeds.RequestStatusResult
import com.valutemonitorwidget.domain.interfaces.CurrencyRepository
import com.valutemonitorwidget.domain.models.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryImpl(private val api: API) : CurrencyRepository {

    override fun getCurrencies(): Flow<RequestStatusResult<List<Currency>>> = flow {
        emit(api.getCurrencies().determineStatus {
            it.valute.values.map { it1 -> Currency(it1.id, it1.charCode, it1.name, it1.value) }
        })
    }
}