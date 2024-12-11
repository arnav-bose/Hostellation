package com.arnav.home.domain.property

import com.arnav.core.domain.DomainModel

data class PropertyCardModel(
    val id: String,
    val imageList: List<String>,
    val name: String,
    val rating: String,
    val distance: String,
    // Facilities
    val lowestPrivatePrice: Float,
    val lowestPrivatePricePerNight: String,
    val lowestDormPrice: Float,
    val lowestDormPricePerNight: String,
    val description: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
): DomainModel
