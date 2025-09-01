package org.riezki.dummyjsonapp.core.data.remote.payload

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int? = null,
)