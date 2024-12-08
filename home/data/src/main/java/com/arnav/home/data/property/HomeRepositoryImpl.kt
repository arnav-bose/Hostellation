package com.arnav.home.data.property

import com.arnav.core.data.network.safeCall
import com.arnav.core.domain.DataError
import com.arnav.core.domain.Result
import com.arnav.core.domain.mapToDomainData
import com.arnav.home.data.APIEndpoints
import com.arnav.home.data.HomeServices
import com.arnav.home.domain.property.HomeRepository
import com.arnav.home.domain.property.PropertyDetails

class HomeRepositoryImpl(
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
}
