package org.riezki.dummyjsonapp.core.utils

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @author riezky maisyar
 */

object HttpFactory {

    fun create() : HttpClient = HttpClient {
        install(Logging) { level = LogLevel.ALL }
        install(ContentNegotiation) {
            json(jsonOption)
        }
    }

    val jsonOption = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
    }
}