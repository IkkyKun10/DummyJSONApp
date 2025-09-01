package org.riezki.dummyjsonapp.core.utils

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import org.riezki.dummyjsonapp.core.domain.AppResult
import org.riezki.dummyjsonapp.core.domain.DataError
import kotlin.coroutines.coroutineContext

/**
 * @author riezky maisyar
 */

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
) : AppResult<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Remote.RequestTimeout)
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Remote.NoInternet)
    } catch (e: Exception) {
        e.printStackTrace()
        coroutineContext.ensureActive()
        return AppResult.Error(DataError.Remote.Unknown)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
) : AppResult<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val data = response.body<T>()
                AppResult.Success(data)
            } catch (e: NoTransformationFoundException) {
                e.printStackTrace()
                return AppResult.Error(DataError.Remote.SerializationError)
            }
        }
        408 -> AppResult.Error(DataError.Remote.RequestTimeout)
        429 -> AppResult.Error(DataError.Remote.TooManyRequests)
        in 500..599 -> AppResult.Error(DataError.Remote.ServerError)
        else -> AppResult.Error(DataError.Remote.Unknown)
    }
}