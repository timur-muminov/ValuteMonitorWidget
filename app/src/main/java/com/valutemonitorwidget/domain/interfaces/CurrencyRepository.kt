package com.valutemonitorwidget.domain.interfaces

import com.valutemonitorwidget.domain.sealeds.RequestStatusResult
import com.valutemonitorwidget.domain.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
     fun getCurrencies(): Flow<RequestStatusResult<List<Currency>>>
}