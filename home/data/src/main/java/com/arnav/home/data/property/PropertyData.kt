package com.arnav.home.data.property

import com.arnav.core.data.NetworkData
import com.arnav.home.domain.property.PropertyCardModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PropertyData(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("imagesGallery") val imageList: List<ImageData>?,
    @Expose @SerializedName("name") val propertyName: String?,
    @Expose @SerializedName("overallRating") val rating: RatingData?,
    @Expose @SerializedName("distance") val distance: DistanceData?,
    @Expose @SerializedName("facilities") val facilities: List<FacilityData>?,
    @Expose @SerializedName("lowestPrivatePricePerNight") val lowestPrivatePrice: PriceData?,
    @Expose @SerializedName("lowestDormPricePerNight") val lowestDormPrice: PriceData?,
    @Expose @SerializedName("overview") val propertyDescription: String?,
    @Expose @SerializedName("address1") val address: String?,
    @Expose @SerializedName("latitude") val latitude: Double?,
    @Expose @SerializedName("longitude") val longitude: Double?
) : NetworkData<PropertyCardModel> {
    override fun convertToDomainData(): PropertyCardModel {
        return PropertyCardModel(
            id = id.toString(),
            imageList = imageList?.map { "https://" + it.prefix + it.suffix } ?: emptyList(),
            name = propertyName ?: "",
            rating = rating?.rating.toString(),
            distance = distance?.value?.toString() + " " + distance?.units,
            lowestPrivatePrice = lowestPrivatePrice?.value?.toFloat() ?: 0f,
            lowestDormPricePerNight = (lowestDormPrice?.value + lowestDormPrice?.currency) ?: "",
            lowestDormPrice = lowestDormPrice?.value?.toFloat() ?: 0f,
            lowestPrivatePricePerNight = (lowestPrivatePrice?.value + lowestPrivatePrice?.currency)
                ?: "",
            description = propertyDescription ?: "",
            address = address ?: "",
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0
        )
    }

}

data class PriceData(
    @Expose @SerializedName("value") val value: String?,
    @Expose @SerializedName("currency") val currency: String?
)

data class FacilityData(
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("id") val id: String?,
    @Expose @SerializedName("facilities") val facilities: List<FacilityData>?
)

data class DistanceData(
    @Expose @SerializedName("value") val value: Double?,
    @Expose @SerializedName("units") val units: String?
)

data class ImageData(
    @Expose @SerializedName("prefix") val prefix: String?,
    @Expose @SerializedName("suffix") val suffix: String?
)

data class RatingData(
    @Expose @SerializedName("overall") val rating: Int?,
    @Expose @SerializedName("numberOfRatings") val numberOfRatings: String?
)