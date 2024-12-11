package com.arnav.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arnav.home.domain.property.PropertyCardModel
import com.arnav.home.domain.property.currency.CurrencyRateMap
import java.util.Locale

private val threePagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (0.9 * availableSpace - 2 * pageSpacing).toInt()
    }
}

@Composable
fun PropertyDetailSheet(
    data: PropertyCardModel,
    rates: CurrencyRateMap,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        // Image Carousel
        val pagerState = rememberPagerState { data.imageList.size }
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            state = pagerState,
            pageSize = threePagesPerViewport,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
        ) { index ->
            val imageURL = data.imageList[index]
            AsyncImage(
                model = imageURL,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Details
        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp)) {
            Column(modifier.weight(1f)) {
                Text(
                    data.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(18f, TextUnitType.Sp)
                )
                if (data.address.isNotBlank()) Text(
                    data.address,
                    fontSize = TextUnit(12f, TextUnitType.Sp)
                )
                if (data.description.isNotBlank()) Text(
                    data.description,
                    fontSize = TextUnit(12f, TextUnitType.Sp)
                )
                if (data.distance.isNotBlank()) Text(
                    data.distance + " " + stringResource(R.string.from_city_center),
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Blue
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
                .padding(start = 8.dp, end = 8.dp, top = 14.dp, bottom = 12.dp)
                .background(Color.LightGray)
                .height(1.dp)
        )

        // Pricing
        PropertyDetailPriceSection(
            data,
            rates,
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
fun PropertyDetailPriceSection(
    data: PropertyCardModel,
    currencyRateMap: CurrencyRateMap,
    modifier: Modifier = Modifier,
) {
    val selectedCurrency = remember { "GBP" }
    Text(
        stringResource(R.string.pricing),
        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
        fontSize = TextUnit(16f, TextUnitType.Sp),
        fontWeight = FontWeight.Bold
    )
    Column {
        Row {
            Image(
                painter = painterResource(com.arnav.core.presentation.ui.R.drawable.ic_room),
                null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(Color.Gray)
            )

            Text(
                convertPrice(
                    data.lowestPrivatePrice,
                    currencyRateMap.ratesMap[selectedCurrency] ?: 1f
                ) +
                        " " +
                        selectedCurrency,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Row {
            Image(
                painter = painterResource(com.arnav.core.presentation.ui.R.drawable.ic_dorm),
                null,
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                convertPrice(
                    data.lowestDormPrice,
                    currencyRateMap.ratesMap[selectedCurrency] ?: 1f
                ) +
                        " " +
                        selectedCurrency,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
  /*  Row(modifier = modifier) {
        Image(
            painter = painterResource(com.arnav.core.presentation.ui.R.drawable.ic_room),
            null,
            modifier = Modifier
                .padding(end = 4.dp)
                .align(Alignment.CenterVertically),
            colorFilter = ColorFilter.tint(Color.Gray)
        )

        Text(
            convertPrice(
                data.lowestPrivatePrice,
                currencyRateMap.ratesMap[selectedCurrency] ?: 1f
            ) +
                    " " +
                    selectedCurrency,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Box(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .width(1.dp)
                .height(30.dp)
                .background(Color.LightGray)
        )

        Image(
            painter = painterResource(com.arnav.core.presentation.ui.R.drawable.ic_dorm),
            null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier
                .padding(end = 4.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            convertPrice(
                data.lowestDormPrice,
                currencyRateMap.ratesMap[selectedCurrency] ?: 1f
            ) +
                    " " +
                    selectedCurrency,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }*/
}

private fun convertPrice(euroPrice: Float, exchangeRate: Float): String {
    val newPrice = euroPrice * exchangeRate
    return String.format(Locale.getDefault(), "%.0f", newPrice)
}


@Preview
@Composable
private fun PropertyDetailPreview() {
    PropertyDetailSheet(
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
            lowestDormPrice = 500f,
            lowestPrivatePricePerNight = "1000 Euro",
            lowestPrivatePrice = 1000f,
            lowestDormPricePerNight = "500 Euro",
            description = "We're a stone's throw from Temple Bar, Connell Bridge, Trinity College, Dublin Castle and much, much more!",
            address = "29 Bachelors Walk, Dublin",
            latitude = 1.0,
            longitude = 20.0
        ),
        CurrencyRateMap(
            hashMapOf(
                Pair("EUR", 1f),
                Pair("GBP", 0.853825f),
                Pair("USD", 1.088993f),
            )
        ),
        modifier = Modifier.background(Color.White, RoundedCornerShape(14.dp))
    )
}