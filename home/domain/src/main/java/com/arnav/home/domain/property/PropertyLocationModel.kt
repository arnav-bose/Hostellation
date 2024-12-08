package com.arnav.home.domain.property

import com.arnav.core.domain.DomainModel

data class PropertyLocationModel(
    val cityName: String,
    val countryName: String
): DomainModel
