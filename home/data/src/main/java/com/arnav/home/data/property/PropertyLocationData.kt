package com.arnav.home.data.property

import com.arnav.core.data.NetworkData
import com.arnav.home.domain.property.PropertyLocationModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PropertyLocationData(
    @Expose @SerializedName("city") val cityData: CityData?
): NetworkData<PropertyLocationModel> {
    override fun convertToDomainData(): PropertyLocationModel {
        return PropertyLocationModel(
            cityName = cityData?.locationName ?: "",
            countryName = cityData?.countryName ?: ""
        )
    }
}

data class CityData(
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("name") val locationName: String?,
    @Expose @SerializedName("country") val countryName: String?
)
