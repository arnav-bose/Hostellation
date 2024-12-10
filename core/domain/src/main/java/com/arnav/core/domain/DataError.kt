package com.arnav.core.domain

sealed interface DataError: ErrorInterface {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

}