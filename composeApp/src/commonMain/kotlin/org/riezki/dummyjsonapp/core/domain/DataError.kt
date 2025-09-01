package org.riezki.dummyjsonapp.core.domain

/**
 * @author riezky maisyar
 */

sealed interface DataError : ErrorApp {
    enum class Remote: DataError {
        RequestTimeout,
        NoInternet,
        TooManyRequests,
        SerializationError,
        ServerError,
        Unknown
    }
}