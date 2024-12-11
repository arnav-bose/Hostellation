package com.arnav.home.data.property

import com.arnav.core.data.network.safeCall
import com.arnav.core.domain.DataError
import com.arnav.core.domain.Result
import com.arnav.core.domain.mapToDomainData
import com.arnav.home.data.APIEndpoints
import com.arnav.home.data.HomeServices
import com.arnav.home.data.currency.CurrencyRateResponseData
import com.arnav.home.domain.property.HomeRepository
import com.arnav.home.domain.property.PropertyDetails
import com.arnav.home.domain.property.currency.CurrencyRateMap
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServices: HomeServices
) : HomeRepository {

    override suspend fun getPropertyDetails(): Result<PropertyDetails, DataError> {
        val result = safeCall<PropertyResponseData> {
            homeServices.getPropertyDetails(
                APIEndpoints.API_PROPERTIES
            )
        }
        return result.mapToDomainData { it.convertToDomainData() }
    }

    override suspend fun getCurrencyRates(): Result<CurrencyRateMap, DataError> {
        val result = safeCall<CurrencyRateResponseData> {
            homeServices.getCurrencyRates(
                APIEndpoints.API_RATES
            )
        }
        return result.mapToDomainData { it.convertToDomainData() }
    }
}
