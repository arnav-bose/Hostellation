package com.arnav.core.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ImageCarousel(
    imageURLList: List<String>,
    pageSize: PageSize,
    pageSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { imageURLList.size }
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = pageSize,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding
    ) { index ->
        val imageURL = imageURLList[index]
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
}

@Preview
@Composable
private fun ImageCarouselPreview() {
    ImageCarousel(
        imageURLList = listOf(
            "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/36.jpg",
            "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/qzseav8zdfqpugqjpvlj",
            "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto/v1/propertyimages/1/100/m8jdgtwpgjzdhezfeipk"
        ),
        PageSize.Fill,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}