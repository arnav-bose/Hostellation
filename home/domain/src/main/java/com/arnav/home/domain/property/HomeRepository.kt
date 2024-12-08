package com.arnav.home.domain.property

import com.arnav.core.domain.DataError
import com.arnav.core.domain.Result

interface HomeRepository {
    suspend fun getPropertyDetails(): Result<PropertyDetails, DataError>
}