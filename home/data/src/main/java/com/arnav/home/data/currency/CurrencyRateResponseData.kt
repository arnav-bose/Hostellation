package com.arnav.home.data.currency

import com.arnav.core.data.NetworkData
import com.arnav.home.domain.property.currency.CurrencyRateMap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyRateResponseData(
    @Expose @SerializedName("rates") val ratesMap: HashMap<String, Float>?
): NetworkData<CurrencyRateMap> {
    override fun convertToDomainData(): CurrencyRateMap {
        val map = ratesMap?.filter { it.key == "GBP" || it.key == "EUR" || it.key == "USD" } ?: emptyMap()
        return CurrencyRateMap(HashMap(map))
    }
}
