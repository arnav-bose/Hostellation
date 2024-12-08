package com.arnav.core.data.network

import com.arnav.core.domain.DataError
import com.arnav.core.domain.Result
import kotlinx.coroutines.CancellationException
import retrofit2.Response
import java.nio.channels.UnresolvedAddressException

suspend inline fun <reified T> responseToResult(response: Response<*>): Result<T, DataError> {
    return when(response.code()) {
        in 200..299 -> Result.Success(response.body() as T)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.INTERNAL_SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(execute: () -> Response<T?>): Result<T, DataError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}