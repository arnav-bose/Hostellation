package com.arnav.home.data

import com.arnav.home.data.property.PropertyResponseData
import retrofit2.Response
import retrofit2.http.Url

interface HomeServices {
    suspend fun getPropertyDetails(@Url url: String): Response<PropertyResponseData?>
}