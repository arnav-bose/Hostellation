package com.arnav.home.data

import com.arnav.home.data.currency.CurrencyRateResponseData
import com.arnav.home.data.property.PropertyResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeServices {

    @GET
    suspend fun getPropertyDetails(@Url url: String): Response<PropertyResponseData?>

    @GET
    suspend fun getCurrencyRates(@Url url: String): Response<CurrencyRateResponseData?>
}