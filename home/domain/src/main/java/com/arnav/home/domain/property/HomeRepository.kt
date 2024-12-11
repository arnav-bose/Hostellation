package com.arnav.home.domain.property

import com.arnav.core.domain.DataError
import com.arnav.core.domain.Result
import com.arnav.home.domain.property.currency.CurrencyRateMap

interface HomeRepository {
    suspend fun getPropertyDetails(): Result<PropertyDetails, DataError>
    suspend fun getCurrencyRates(): Result<CurrencyRateMap, DataError>
}