package com.arnav.home.data.property

import com.arnav.core.data.NetworkData
import com.arnav.home.domain.property.PropertyDetails
import com.arnav.home.domain.property.PropertyLocationModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PropertyResponseData(
    @Expose @SerializedName("properties") val properties: List<PropertyData>?,
    @Expose @SerializedName("location") val propertyLocationData: PropertyLocationData?
): NetworkData<PropertyDetails> {
    override fun convertToDomainData(): PropertyDetails {
        return PropertyDetails(
            propertyList = properties?.map { it.convertToDomainData() } ?: emptyList(),
            propertyLocationModel = propertyLocationData?.convertToDomainData() ?: PropertyLocationModel("", "")
        )
    }
}
