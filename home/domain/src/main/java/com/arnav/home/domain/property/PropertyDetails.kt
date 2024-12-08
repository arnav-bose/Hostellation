package com.arnav.home.domain.property

import com.arnav.core.domain.DomainModel

data class PropertyDetails(
    val propertyList: List<PropertyCardModel>,
    val propertyLocationModel: PropertyLocationModel
): DomainModel
