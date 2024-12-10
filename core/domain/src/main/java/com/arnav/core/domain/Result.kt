package com.arnav.core.domain

sealed interface Result<out D, out E: ErrorInterface> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<E: ErrorInterface>(val error: E) : Result<Nothing, E>
}

inline fun <NetworkData, E: ErrorInterface, DomainData> Result<NetworkData, E>.mapToDomainData(
    map: (NetworkData) -> DomainData): Result<DomainData, E> {
        return when(this) {
            is Result.Error -> Result.Error(error)
            is Result.Success -> Result.Success(map(data))
        }
    }