package com.arnav.core.domain

sealed interface ScreenOverlayData {
    data object None: ScreenOverlayData
    data object Loading : ScreenOverlayData
    data class Error(val error: DataError) : ScreenOverlayData
}