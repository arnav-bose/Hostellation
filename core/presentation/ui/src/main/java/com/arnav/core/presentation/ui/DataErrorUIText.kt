package com.arnav.core.presentation.ui

import com.arnav.core.domain.DataError

fun DataError.asUIText(): UIText {
    return when(this) {
        DataError.Network.REQUEST_TIMEOUT -> UIText.StringRes(R.string.error_request_timeout)
        DataError.Network.TOO_MANY_REQUESTS -> UIText.StringRes(R.string.error_too_many_requests)
        DataError.Network.NO_INTERNET -> UIText.StringRes(R.string.error_no_internet)
        DataError.Network.NOT_FOUND -> UIText.StringRes(R.string.error_server_not_found)
        DataError.Network.INTERNAL_SERVER_ERROR -> UIText.StringRes(R.string.error_internal_server)
        DataError.Network.SERIALIZATION -> UIText.StringRes(R.string.error_serialization)
        DataError.Network.UNKNOWN -> UIText.StringRes(R.string.error_unknown)
    }
}