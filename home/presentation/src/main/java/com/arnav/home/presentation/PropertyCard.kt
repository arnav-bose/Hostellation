package com.arnav.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arnav.core.presentation.ui.ImageCarousel
import com.arnav.core.presentation.ui.RatingSnippet
import com.arnav.home.domain.property.PropertyCardModel
import java.util.Locale

@Composable
fun PropertyCard(
    data: PropertyCardModel,
    modifier: Modifier = Modifier,
    onPropertyClick: (propertyDetails: PropertyCardModel) -> Unit
) {
    Column(modifier = modifier.clickable { onPropertyClick.invoke(data) }) {
        Box {
            ImageCarousel(
                imageURLList = data.imageList,
                pageSize = PageSize.Fill,
                pageSpacing = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            if (data.isFeatured) {
                Text(
                    stringResource(R.string.featured), color = Color.White, fontSize = TextUnit(12f, TextUnitType.Sp), fontWeight = FontWeight.Medium, modifier = Modifier
                        .padding(top = 24.dp)
                        .background(Color.Blue, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .padding(start = 4.dp, end = 10.dp, top = 2.dp, bottom = 2.dp)
                )
            }
        }
        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Column(modifier.weight(1f)) {
                Text(
                    data.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(18f, TextUnitType.Sp)
                )
                Text(data.address, fontSize = TextUnit(12f, TextUnitType.Sp))
            }

            RatingSnippet(data.rating.toInt())
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                .height(1.dp)
                .background(Color.LightGray)
        )

        Row(modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)) {
            if (data.lowestPrivatePrice > 0) {
                Column {
                    Text("Rooms:")
                    Text((String.format(Locale.getDefault(), "%.0f", data.lowestPrivatePrice)) + " " + data.currency)
                }
            }

            if (data.lowestDormPrice > 0) {
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text("Dorms:")
                    Text((String.format(Locale.getDefault(), "%.0f", data.lowestDormPrice)) + " " + data.currency)
                }
            }
        }
    }
}

@Composable
private fun ImageCarousel(imageList: List<String>, modifier: Modifier) {
    imageList.getOrNull(0)?.let {
        AsyncImage(
            it,
            null,
            modifier = modifier,
            contentScale = ContentScale.Crop,
            clipToBounds = true
        )

    }
}

@Preview
@Composable
private fun PropertyCardPreview() {
    PropertyCard(
        PropertyCardModel(
            id = "1",
            imageList = listOf(
                "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/36.jpg",
                "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/qzseav8zdfqpugqjpvlj",
                "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/m8jdgtwpgjzdhezfeipk"
            ),
            name = "Abbey Court",
            rating = "45",
            isFeatured = true,
            distance = "1.2 km",
            lowestDormPrice = 500f,
            lowestPrivatePricePerNight = "1000 Euro",
            lowestPrivatePrice = 1000f,
            lowestDormPricePerNight = "500 Euro",
            description = "We're a stone's throw from Temple Bar, Connell Bridge, Trinity College, Dublin Castle and much, much more!",
            address = "29 Bachelors Walk, Dublin",
            currency = "EUR",
            latitude = 1.0,
            longitude = 20.0
        ),
        modifier = Modifier.background(Color.White, RoundedCornerShape(14.dp)),
        { }
    )
}