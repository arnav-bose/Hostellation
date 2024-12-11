package com.arnav.home.domain.property.currency

import com.arnav.core.domain.DomainModel

data class CurrencyRateMap(
    val ratesMap: HashMap<String, Float>
): DomainModel