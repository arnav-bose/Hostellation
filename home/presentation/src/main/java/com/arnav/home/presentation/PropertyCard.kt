package com.arnav.home.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arnav.home.domain.property.PropertyCardModel

@Composable
fun PropertyCard(data: PropertyCardModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ImageCarousel(
            imageList = data.imageList, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Column(modifier.weight(1f)) {
                Text(
                    data.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(18f, TextUnitType.Sp)
                )
                Text(data.address, fontSize = TextUnit(12f, TextUnitType.Sp))
                Text(
                    data.description,
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .background(Color.Green, RoundedCornerShape(4.dp))
                    .padding(2.dp)
            ) {
                Text(data.rating, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                .height(1.dp)
                .background(Color.Gray)
        )

        Row(modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)) {
            Column {
                Text("Rooms:")
                Text(data.lowestPrivatePricePerNight)
            }

            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text("Dorms:")
                Text(data.lowestDormPricePerNight)
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
            rating = "4.5",
            distance = "1.2 km",
            lowestPrivatePricePerNight = "1000 Euro",
            lowestDormPricePerNight = "500 Euro",
            description = "We're a stone's throw from Temple Bar, Connell Bridge, Trinity College, Dublin Castle and much, much more!",
            address = "29 Bachelors Walk, Dublin",
            latitude = 1.0,
            longitude = 20.0
        ),
        modifier = Modifier.background(Color.White, RoundedCornerShape(14.dp))
    )
}