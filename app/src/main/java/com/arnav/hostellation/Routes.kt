package com.arnav.hostellation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Home: Routes

    @Serializable
    data class HomeListing(val id: Int): Routes

}