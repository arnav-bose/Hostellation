package com.arnav.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arnav.core.presentation.ui.overlay.ScreenOverlay
import com.arnav.home.domain.property.currency.CurrencyRateMap

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel
) {
    HomeScreen(viewModel = viewModel, modifier = Modifier.background(Color.Gray))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel? = null) {

    viewModel ?: return

    val propertyListState = viewModel.propertyList.collectAsStateWithLifecycle()
    val overlayState = viewModel.overlayStateFlow.collectAsStateWithLifecycle()
    val selectedPropertyModel = viewModel.selectedPropertyModel.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(all = 12.dp)
    ) {
        items(propertyListState.value.size) {
            val propertyCardModel = propertyListState.value.getOrNull(it)
            propertyCardModel?.let {
                PropertyCard(
                    propertyCardModel,
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(14.dp))
                ) { propertyCardModel ->
                    viewModel.setSelectedProperty(propertyCardModel)
                }
            }
        }
    }

    if (selectedPropertyModel.value != null) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.setSelectedProperty(null) },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            PropertyDetailSheet(
                selectedPropertyModel.value!!,
                viewModel.currencyRateMap ?: CurrencyRateMap(hashMapOf()),
                modifier = Modifier
                    .background(Color.White)
            )
        }
    }

    ScreenOverlay(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        data = overlayState.value
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}